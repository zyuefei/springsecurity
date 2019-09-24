package com.portjs.base.aop;

import java.lang.annotation.*;

/**
 * @author zhangyuefei
 * @version 1.0
 * @date 2019/9/20 5:47 下午
 */

@Target({ ElementType.FIELD}) // 注解放置的目标位置，类属性上
@Retention(RetentionPolicy.RUNTIME) // 注解在哪个阶段执行
@Documented
public @interface LogShowParam {

    /**
     * 需要在日志中展示的字段描述
     * @return
     */
    String value() default "";
}
