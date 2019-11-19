package com.v.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解在另一个注解上使用
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventBase {
    //1、订阅关系
    String listenerSetter();

    //2、事件本身
    Class<?> listenerType();

    //3、时间处理程序
    String callbackMetod();
}
