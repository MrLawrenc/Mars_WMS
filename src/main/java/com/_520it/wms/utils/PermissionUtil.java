package com._520it.wms.utils;

import java.lang.reflect.Method;

public class PermissionUtil {
    //拼接方法的权限表达式
    public static String buildExpression(Method method) {
        //获取当前方法所在类的全限定名称
        String className = className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        return className + ":" + methodName;
    }
}
