package com._520it.wms.service.impl;


import com._520it.wms.dao.IRoleDAO;
import com._520it.wms.domain.Role;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IRoleService;

import java.util.List;

public class RoleServiceImpl implements IRoleService {
    @lombok.Setter
    private IRoleDAO roleDAO;

    @Override
    public void save(Role r) {
        roleDAO.save(r);
    }

    @Override
    public void update(Role r) {
        roleDAO.update(r);
    }

    @Override
    public void delete(Long id) {
        roleDAO.delete(id);
    }

    @Override
    public Role get(Long id) {
        return roleDAO.get(id);
    }

    @Override
    public List<Role> listAll() {
        return roleDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return roleDAO.query(qo);
    }
}
