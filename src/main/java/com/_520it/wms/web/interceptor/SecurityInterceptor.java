package com._520it.wms.web.interceptor;


import com._520it.wms.domain.Employee;
import com._520it.wms.utils.PermissionUtil;
import com._520it.wms.utils.RequiredPermission;
import com._520it.wms.utils.UserContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.lang.reflect.Method;
import java.util.Set;

//权限检查拦截器
public class SecurityInterceptor extends AbstractInterceptor {
    //检查session中是否有USER_IN_SESSION，如果有则放行，没有就回到登录界面
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Employee user = UserContext.getEmployee();
        //1.如果用户存在而且为超级管理员则放行
        if (user != null && user.isAdmin()) {
            return actionInvocation.invoke();
        }
        //2.判断当前访问的方法上是否有requriedPermission注解标签，如果没有（说明不需要权限），则直接放行
        String methodName = actionInvocation.getProxy().getMethod();//当前访问action的方法名称
        Method actionMethod = actionInvocation.getProxy().getAction().getClass().getDeclaredMethod(methodName);//根据action找到         里面对应的方法
        RequiredPermission rp = actionMethod.getAnnotation(RequiredPermission.class);//得到该方法上RequiredPermission标签
        if (rp == null) {
            return actionInvocation.invoke();
        }
        //3.获取当前请求的action方法的权限表达式
        String exp = PermissionUtil.buildExpression(actionMethod);
        //4.判断当前session中是否有action方法对应的权限表达式
        Set<String> permissions = UserContext.getExpression();
        if (permissions.contains(exp)) {
            return actionInvocation.invoke();
        }
        return "nopermission";
    }
}
