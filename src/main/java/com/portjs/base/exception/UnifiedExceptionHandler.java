package com.portjs.base.exception;

import com.portjs.base.util.ResponseMessage;
import com.portjs.base.util.ResultCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class UnifiedExceptionHandler {
    private final Logger logger =  LoggerFactory.getLogger(Exception.class);
    public static String method;

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
//    @ResponseStatus(HttpStatus.HTTP_VERSION_NOT_SUPPORTED)
    public ResponseMessage unifiedExceptionHandler(Exception e){
//        logger.error(tag + "updatePeopleGd() end===>" + e);
        ResponseMessage responseMessage = new ResponseMessage(ResultCodeEnum.SERVERERROR,"");
        logger.error(method+e);  //这里必须是logger.error(str1,str2),需要在
        e.printStackTrace();
        return responseMessage;
    }

}
