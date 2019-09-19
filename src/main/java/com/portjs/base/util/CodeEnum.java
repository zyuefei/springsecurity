package com.portjs.base.util;

public enum CodeEnum {
    SUCCESS("200", "成功"),
    ERROR("500", "失败"),
    SERVERERROR("5000", "服务器异常"),
    DUPLICATENAME("5001", "名字重复:%1$s=%2$s"),
    REQUESTPARAMERROR("4000","参数:%1$s缺失"),
    NOTMEETTHERULES("4005","参数:%1$s不符合规则"),
    NOTFUND("4001","请求参数id不存在"),
    UNAUTHORIZED("4002","当前用户未登陆"),
    AUTHENTICATEERROR("4003","认证错误"),
    NOTFUNDENTITY("4004","数据库不存在改对象"),
    AuthenticationException("4010","系统认证异常"),
    NOPASSPASSWORDLENGTH("4011","密码长度不符合规则"),
    PARAM_PARSE_ERROR("400", "参数解析失败"),
    PARAM_VALID_ERROR("400", "参数校验失败"),
    PARAM_TYPE_ERROR("400", "参数类型不正确"),
    /**
     * 权限异常：300-399
     */
    REQUIRES_PERMISSIONS_ERROR("403", "权限不足"),

    /**
     * 请求错误：400-499
     */
    RESOURCES_ALREADY_EXIST_ERROR("400", "资源已经存在"),

    /**
     * 请求错误：400-499
     */
    RESOURCES_FOREIGN_KEY_CONSTRAINT_ERROR("400", "有关联数据，无法删除"),

    /**
     * 请求错误：400-499
     */
    DATA_TOO_LONG_EXCEPTION("400", "数据长度超过最大值"),

    /**
     * 请求错误：400-499
     */
    DATA_INTEGRITY_VIOLATION_ERROR("400", "数据完整性异常"),

    /**
     * Feign错误：500-599
     */
    FEIGN_ERROR("500", "服务调用错误"),

    /**
     * 服务器错误：500-599
     */
    SERVER_ERROR("500", "服务器错误"),

    /**
     * 数据错误：500-599
     */
    TOO_MANY_RESULT_ERROR("500", "数据异常：有超过1个查询结果"),

    /**
     * 未知异常
     */
    UNKNOWN_ERROR("999", "未知异常")
    ;


    private String code;
    private String msg;

    CodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResultCodeEnum{" +
            "code=" + code +
            ", msg='" + msg + '\'' +
            '}';
    }
}
