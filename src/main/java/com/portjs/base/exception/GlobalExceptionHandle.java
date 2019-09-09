package com.portjs.base.exception;

import com.portjs.base.util.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

/**
 * 统一异常处理
 *
 * @author bianchangcai
 * @date 2018/8/21 18:22
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandle {



    /**
     * 参数解析异常
     *
     * @param e HttpMessageNotReadableException
     * @return 错误响应对象
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorBean httpMessageNotReadableExceptionHandle(HttpMessageNotReadableException e) {
        log.error("参数解析异常", e);
        return ErrorBeanFactory.getError(ResultCodeEnum.PARAM_PARSE_ERROR.getCode(),
                ResultCodeEnum.PARAM_PARSE_ERROR.getMsg());
    }

    /**
     * 参数类型不正确异常
     *
     * @param e MethodArgumentTypeMismatchException
     * @return 错误响应对象
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorBean methodArgumentTypeMismatchExceptionHandle(MethodArgumentTypeMismatchException e) {
        log.error("参数类型不正确", e);
        return ErrorBeanFactory.getError(ResultCodeEnum.PARAM_TYPE_ERROR.getCode(),
                ResultCodeEnum.PARAM_TYPE_ERROR.getMsg() + ":" + e.getValue());
    }

    /**
     * 参数验证失败
     *
     * @param e MethodArgumentNotValidException
     * @return 错误响应对象
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorBean httpMessageNotReadableExceptionHandle(MethodArgumentNotValidException e) {

        // 获取校验异常信息
        StringBuilder sb = new StringBuilder();
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        for (ObjectError error : errors) {
            sb.append(error.getDefaultMessage()).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);

        log.error("参数校验失败：{}", sb.toString(), e);
        return ErrorBeanFactory.getError(ResultCodeEnum.PARAM_VALID_ERROR.getCode(),
                ResultCodeEnum.PARAM_VALID_ERROR.getMsg() + ":" + sb);
    }

    /**
     * 缺少参数或参数不能为空异常
     *
     * @param e MissingServletRequestParameterException
     * @return 缺少参数
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorBean missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("参数校验失败", e);
        return ErrorBeanFactory.getError(ResultCodeEnum.PARAM_VALID_ERROR.getCode(),
                ResultCodeEnum.PARAM_VALID_ERROR.getMsg());
    }

//    /**
//     * 权限不足异常
//     *
//     * @param e UnauthorizedException
//     * @return 错误响应对象
//     */
//    @ExceptionHandler(UnauthorizedException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public ErrorBean unauthorizedExceptionHandle(UnauthorizedException e) {
//        log.error("权限不足", e);
//        return ErrorBeanFactory.getError(ResultCodeEnum.REQUIRES_PERMISSIONS_ERROR.getCode(),
//                ResultCodeEnum.REQUIRES_PERMISSIONS_ERROR.getCnMsg(),
//                ResultCodeEnum.REQUIRES_PERMISSIONS_ERROR.getEnMsg());
//    }

    /**
     * 资源已经存在异常
     *
     * @param e DuplicateKeyException
     * @return 错误响应对象
     */
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorBean duplicateKeyExceptionHandle(DuplicateKeyException e) {
        log.error("资源已经存在", e);
        return ErrorBeanFactory.getError(ResultCodeEnum.RESOURCES_ALREADY_EXIST_ERROR.getCode(),
                ResultCodeEnum.RESOURCES_ALREADY_EXIST_ERROR.getMsg());
    }

    /**
     * 数据完整性异常
     *
     * @param e DataIntegrityViolationException
     * @return 错误响应对象
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorBean dataIntegrityViolationExceptionHandle(DataIntegrityViolationException e) {

        String message = e.getCause().getMessage();

        // 待优化
        if (message.contains("Cannot delete or update a parent row")) {

            // 有外键关联，不能删除异常
            log.error("有关联数据，无法删除", e);
            return ErrorBeanFactory.getError(ResultCodeEnum.RESOURCES_FOREIGN_KEY_CONSTRAINT_ERROR.getCode(),
                    ResultCodeEnum.RESOURCES_FOREIGN_KEY_CONSTRAINT_ERROR.getMsg());
        } else if (message.contains("Data too long for column")) {

            // 字段长度超过最大长度异常
            log.error("字段长度超过最大长度限制", e);
            return ErrorBeanFactory.getError(ResultCodeEnum.DATA_TOO_LONG_EXCEPTION.getCode(),
                    ResultCodeEnum.DATA_TOO_LONG_EXCEPTION.getMsg());
        }

        log.error("数据完整性异常", e);
        return ErrorBeanFactory.getError(ResultCodeEnum.DATA_INTEGRITY_VIOLATION_ERROR.getCode(),
                ResultCodeEnum.DATA_INTEGRITY_VIOLATION_ERROR.getMsg() + e.getCause().getMessage());
    }

//    /**
//     * 服务调用异常
//     */
//    @ExceptionHandler(FeignException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorBean feignExceptionHandle(FeignException e) {
//
//        log.error("Feign服务调用异常", e);
//        String message = e.getMessage();
//        String jsonString = message.substring(message.indexOf(CommonConstant.COMMON_LEFT_BRACES));
//        JSONObject jsonObject = JSONObject.parseObject(jsonString);
//        String map = jsonObject.getString(CommonConstant.RESPONSE_I18MSGS);
//        JSONObject msgJsonObject = JSONObject.parseObject(map);
//        String cnMsg = msgJsonObject.getString(CommonConstant.LANGUAGE_CN);
//        String enMsg = msgJsonObject.getString(CommonConstant.LANGUAGE_EN);
//
//        return ErrorBeanFactory.getError(400, cnMsg, enMsg);
//
//    }

    /**
     * 其它未知异常
     *
     * @param e Exception
     * @return 错误响应对象
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorBean unknownExceptionHandle(Exception e) {

        // 查询到多个结果异常
        if (e.getCause().getMessage().contains("Expected one result (or null) to be returned by selectOne()")) {
            log.error("查询到多个结果", e);
            return ErrorBeanFactory.getError(ResultCodeEnum.TOO_MANY_RESULT_ERROR.getCode(),
                    ResultCodeEnum.TOO_MANY_RESULT_ERROR.getMsg());

        }

        log.error("未知异常", e);
        return ErrorBeanFactory.getError(ResultCodeEnum.UNKNOWN_ERROR.getCode(),
                ResultCodeEnum.UNKNOWN_ERROR.getMsg());
    }

}