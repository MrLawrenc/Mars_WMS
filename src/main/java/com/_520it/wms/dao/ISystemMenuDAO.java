package com._520it.wms.dao;


import com._520it.wms.domain.Role;
import com._520it.wms.domain.SystemMenu;

import java.util.List;

public interface ISystemMenuDAO extends IGenericDAO<SystemMenu> {
    List<SystemMenu> queryChildrenMenus();

    List<SystemMenu> queryMenusByParentSn(String sn);

    //根据父级菜单的sn和当前登录用户的角色来差尊子菜单
    List<SystemMenu> queryMenusByParentSnAndRole(String sn, List<Role> roles);
}
