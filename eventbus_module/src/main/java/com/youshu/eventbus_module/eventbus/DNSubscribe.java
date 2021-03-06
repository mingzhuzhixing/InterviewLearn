package com.youshu.eventbus_module.eventbus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DNSubscribe {
    DNThreadMode threadMode() default DNThreadMode.POSTING;
}
