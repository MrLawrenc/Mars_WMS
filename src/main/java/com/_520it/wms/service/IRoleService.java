package com._520it.wms.service;


import com._520it.wms.domain.Role;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface   IRoleService {
    void save(Role r);

    void update(Role r);

    void delete(Long id);

    Role get(Long id);

    List<Role> listAll();

    //分页查询
    public PageResult query(QueryObject qo);
}
