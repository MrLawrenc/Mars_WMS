package com._520it.wms.service;


import com._520it.wms.domain.SystemMenu;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.vo.SystemMenuVO;

import java.util.List;

public interface ISystemMenuService {
    void save(SystemMenu systemMenu);

    void update(SystemMenu systemMenu);

    void delete(Long id);

    SystemMenu get(Long id);

    List<SystemMenu> listAll();

    //分页查询
    public PageResult query(QueryObject qo);

    //查询多层的父级菜单列表
    List<SystemMenuVO> queryMenusByParentId(Long parentId);

    //查询出所有的子菜单，不包括根菜单
    List<SystemMenu> queryChildrenMenus();

    //加载动态菜单树
    List<SystemMenu> queryMenusByParentSn(String sn);
}
