package com.portjs.base.util;

/**
 * Created by 许虎飞 on 2018/12/14.
 * 统一请求体返回
 */
public class ResponseMessage {
    private Integer code;
    private String message;
    private Object data;


    /**
     * 更具数据库，增删改 返回值判断是否操作成功返回结果
     * @param index
     */
    public ResponseMessage(int index){
        if (index>=0){
            this.code =Integer.parseInt(CodeEnum.SUCCESS.getCode());
            this.message = CodeEnum.SUCCESS.getMsg();
        }else {
            this.code =Integer.parseInt(CodeEnum.ERROR.getCode());
            this.message = CodeEnum.ERROR.getMsg();
        }
    }

    public ResponseMessage(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseMessage(String code, String message, Object data) {
        this.code = Integer.parseInt(code);
        this.message = message;
        this.data = data;
    }


    public ResponseMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public ResponseMessage(CodeEnum codeEnum, Object data) {
        this.code =Integer.parseInt(codeEnum.getCode());
        this.message = codeEnum.getMsg();
        this.data = data;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
