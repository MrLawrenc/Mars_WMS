package com._520it.wms.service;

import com._520it.wms.domain.Employee;
import com._520it.wms.query.EmployeeQueryObject;
import com._520it.wms.query.PageResult;

import java.util.List;

public interface IEmployeeService {
    void save(Employee e);

    void update(Employee e);

    void delete(Long id);

    Employee get(Long id);

    List<Employee> listAll();

    //分页查询
    public PageResult query(EmployeeQueryObject qo);

    //登录检查
    void checkLogin(String username, String password);

    //批量删除
    void batchDelete(List<Long> ids);

}
