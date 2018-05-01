package com._520it.wms.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com._520it.wms.dao.IEmployeeDAO;
import com._520it.wms.domain.Employee;
import com._520it.wms.domain.Permission;
import com._520it.wms.domain.Role;
import com._520it.wms.query.EmployeeQueryObject;
import com._520it.wms.query.PageResult;
import com._520it.wms.service.IEmployeeService;
import com._520it.wms.utils.MD5;
import com._520it.wms.utils.UserContext;
import lombok.Setter;

public class EmployeeServiceImpl implements IEmployeeService {


    @Setter
    private IEmployeeDAO dao;

    public void save(Employee e) {
        //加密操作
        e.setPassword(MD5.encode(e.getPassword()));
        dao.save(e);
    }

    public void update(Employee e) {
        dao.update(e);
    }

    public void delete(Long id) {
        dao.delete(id);
    }

    public Employee get(Long id) {
        Employee employee = dao.get(id);
        return employee;
    }

    public List<Employee> listAll() {
        List<Employee> all = dao.listAll();
        return all;
    }

    @Override//分页查询
    public PageResult query(EmployeeQueryObject qo) {
        return dao.query(qo);
    }

    @Override
    public void checkLogin(String username, String password) {
        //1.根据username和password查询employee对象
        Employee employee = dao.checkLogin(username, MD5.encode(password));
        if (employee == null)
            throw new RuntimeException("亲，账号或者密码有误！");
        //2.将当前用户存放在session中
        UserContext.setEmployee(employee);
        //判断是否是超级管理员
        if (!employee.isAdmin()) {
            //3.查询出当前用户所拥有的权限
            Set<String> permissionSet = new HashSet<>();//用来存放角色权限的表达式
            List<Role> roles = employee.getRoles();
            for (Role role : roles) {
                List<Permission> ps = role.getPermissions();
                for (Permission p : ps) {
                    permissionSet.add(p.getExpression());
                }
            }
            UserContext.setPermissions(permissionSet);
        }
    }

    @Override
    public void batchDelete(List<Long> ids) {
        dao.batchDelete(ids);
    }
}
