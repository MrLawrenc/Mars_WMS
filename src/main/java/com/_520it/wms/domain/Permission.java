package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

//权限对象
@Getter
@Setter
public class Permission extends BaseDomain {
    private String name;    //表示权限名称 如：员工删除
    private String expression;//权限的表达式 如：ssh.web.action.EmployeeAction：delete
}
