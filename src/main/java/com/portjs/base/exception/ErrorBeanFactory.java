package com.portjs.base.exception;

/**
 * 异常响应对象工厂
 *
 * @author liyawei
 * @date 18-8-27上午10:16
 */
public class ErrorBeanFactory {

    /**
     * 获取一个异常响应对象
     *
     * @param code  错误码
     * @param cnMsg 中文错误消息
     * @return
     */
    public static ErrorBean getError(String code, String cnMsg) {
        return new ErrorBean(code, cnMsg);
    }
}
