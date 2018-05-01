package com._520it.wms.service;



import com._520it.wms.domain.Permission;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;


public interface IPermissionService {
    //删除指定的权限
    void delete(Long id);

    //查询出所有的权限
    List<Permission> listAll();

    //  分页查询
    public PageResult query(QueryObject qo);

    //加在权限
    void reload();
}
