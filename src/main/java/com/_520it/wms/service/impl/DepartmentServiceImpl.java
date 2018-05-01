package com._520it.wms.service.impl;


import com._520it.wms.dao.IDepartmentDAO;
import com._520it.wms.domain.Department;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepartmentService;
import lombok.Setter;

import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {
    @Setter
    private IDepartmentDAO dao;

    public void save(Department e) {
        dao.save(e);
        // throw new RuntimeException("故意抛出的异常");
    }

    public void update(Department e) {
        dao.update(e);
    }

    public void delete(Long id) {
        dao.delete(id);
    }

    public Department get(Long id) {
        Department Department = dao.get(id);
        return Department;
    }

    public List<Department> listAll() {
        List<Department> all = dao.listAll();
        return all;
    }

    @Override//分页查询
    public PageResult query(QueryObject qo) {
        return dao.query(qo);
    }

}
