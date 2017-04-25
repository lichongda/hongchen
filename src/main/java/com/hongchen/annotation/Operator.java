package com.hongchen.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 记录操作日志
 * Created by lichongda on 2017/3/24.
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Operator {
    public String story() default "";//执行方法的动作
    public String name() default "";//类名
    public boolean operatorClass() default true;//是否是标记日志类
}
