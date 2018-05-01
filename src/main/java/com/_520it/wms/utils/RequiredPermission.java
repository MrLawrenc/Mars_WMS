package com._520it.wms.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//贴在方法上
@Retention(RetentionPolicy.RUNTIME)//说明可以存活在JVM里面，可以使用反射赋予该注解功能
public @interface RequiredPermission {
    String value();//表示权限的名称
}
