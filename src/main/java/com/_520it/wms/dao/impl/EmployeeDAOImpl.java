package com._520it.wms.dao.impl;


import com._520it.wms.dao.IEmployeeDAO;
import com._520it.wms.domain.Employee;


public class EmployeeDAOImpl extends GenericDAOImpl<Employee> implements IEmployeeDAO {

    //登录检查
    public Employee checkLogin(String username, String password) {
        return super.queryObject(" obj.name=? AND obj.password=? ", username, password);
    }
}
