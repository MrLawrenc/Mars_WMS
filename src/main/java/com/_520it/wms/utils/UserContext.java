package com._520it.wms.utils;

import com._520it.wms.domain.Employee;
import com.opensymphony.xwork2.ActionContext;

import java.util.Set;

/*
* 用户上下文：存放的是session中用户的信息
* */
public class UserContext {
    private  static  final String USER_IN_SESSION="USER_IN_SESSION";
    private  static  final String PERMISSIONSET_IN_SESSION="PERMISSIONSET_IN_SESSION";
    /*
    * 保存登录用户信息到session中或者从session中注销
    * */
    public static void setEmployee(Employee employee) {
        if (employee != null) {
            //将用户信息保存在session中
            ActionContext.getContext().getSession().put(USER_IN_SESSION, employee);
        } else {
            //如果传入一个null，则表示注销，清空session
            ActionContext.getContext().getSession().clear();
            // 这个方法也可以清空session：ActionContext.getContext().getSession().remove("USER_IN_SESSION");
        }
    }

    /*
    * 从session中得到当前用户登录的信息,便于拦截器检查
    * */
    public static Employee getEmployee() {
        return (Employee) ActionContext.getContext().getSession().get(USER_IN_SESSION);
    }

    /*
    * 在保存用户信息时同时保存当前用户的权限表达式
    * */
    public static void setPermissions(Set<String> permissionSet) {
        ActionContext.getContext().getSession().put(PERMISSIONSET_IN_SESSION, permissionSet);
    }

    /*
    * 获取当前用户所拥有的权限表达式
    * */
    public static Set<String> getExpression() {
        return (Set<String>) ActionContext.getContext().getSession().get(PERMISSIONSET_IN_SESSION);
    }
}
