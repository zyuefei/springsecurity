package com.portjs.base.aop;

import java.lang.annotation.*;

/**
 * @author zhangyuefei
 * @version 1.0
 * @date 2019/9/21 11:02 上午
 */
@Target({ ElementType.TYPE}) // 注解放置的目标位置，类上使用个注解
@Retention(RetentionPolicy.RUNTIME) // 注解在哪个阶段执行
@Documented // 生成文档
public @interface LogModel {

    /**
     * 需要在日志中展示的字段描述
     * @return
     */
    String value() default "";
}
