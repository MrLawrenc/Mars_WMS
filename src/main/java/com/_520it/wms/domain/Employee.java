package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Employee extends BaseDomain {
    private String name;
    private String password;
    private String email;
    private Integer age;
    private boolean admin = false;//是否是超级管理员
    private Department dept;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", admin=" + admin +
                '}';
    }

    //一个用户拥有多个角色，一个角色可以赋予多个用户
    private List<Role> roles = new ArrayList<>();

    //获取用户的角色信息
    public String getRoleNames() {
        if (this.admin)
            return "【超级管理员】";
        if (this.roles.size() == 0)
            return "【暂无角色信息】";
        StringBuilder sb = new StringBuilder(40);
        sb.append("【");
        for (Role role : roles) {
            sb.append(role.getName()).append("，");
        }
        sb.deleteCharAt(sb.length() - 1);//删除最后一个逗号
        sb.append("】");
        return sb.toString();
    }
}
