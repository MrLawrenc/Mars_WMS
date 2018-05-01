package com._520it.wms.web.interceptor;


import com._520it.wms.domain.Employee;
import com._520it.wms.utils.UserContext;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

//登录检查拦截器
public class CheckLoginInterceptor extends AbstractInterceptor {
    @Override
    //检查session中是否有USER_IN_SESSION，如果有则放行，没有就回到登录界面
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Employee current = UserContext.getEmployee();
        if (current == null)
            return Action.LOGIN;
        return actionInvocation.invoke();
    }
}
