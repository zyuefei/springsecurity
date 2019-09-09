package com.portjs.base.exception;

/**
 * 错误信息
 *
 * @author bianchangcai
 * @date 2018/8/21 18:47
 */
public class ErrorBean {

    /**
     * 错误码
     */
    private String code;

    /**
     * 中文错误消息
     */
    private String message;



    public ErrorBean() {
    }

    public ErrorBean(String code, String cnMsg) {
        this.code=code;
        this.message=cnMsg;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}