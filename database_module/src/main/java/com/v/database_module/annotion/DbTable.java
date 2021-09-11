package com.v.database_module.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-05-07 14:39
 */

@Target(ElementType.TYPE)//作用再类上的
@Retention(RetentionPolicy.RUNTIME)//运行时
public @interface DbTable {

    String value();
}
