package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//角色对象
@Getter
@Setter
public class Role extends BaseDomain {
    private String name;//角色名称
    private String sn;//角色编码
    //一个角色可以有多个权限，一个权限也可以赋予多个角色，是many2many关系
    private List<Permission> permissions = new ArrayList<>();
    //一个角色可以有多个菜单，一个菜单也可以赋予多个角色，是many2many关系
    private List<SystemMenu> menus = new ArrayList<>();
}
