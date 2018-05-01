package com._520it.wms.dao;

import com._520it.wms.domain.Employee;


public interface IEmployeeDAO extends IGenericDAO<Employee> {
    //登录检查
    Employee checkLogin(String username, String password);
}