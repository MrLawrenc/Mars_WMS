package com._520it.wms.web.action;

import com._520it.wms.domain.Employee;
import com._520it.wms.query.EmployeeQueryObject;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.service.IEmployeeService;
import com._520it.wms.service.IRoleService;
import com._520it.wms.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class EmployeeAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Setter
    private IEmployeeService employeeService;
    @Getter
    private Employee employee = new Employee();
    @Setter
    private IDepartmentService departmentService;
    @Getter
    private EmployeeQueryObject qo = new EmployeeQueryObject();
    @Setter
    private IRoleService roleService;

    //查询列表
    @RequiredPermission("员工查询")
    @InputConfig(methodName = "input")
    public String execute() {
        try {
            getContext("depts", departmentService.listAll());
            // getContext("employees", employeeService.query(qo));
            getContext("pageResult", employeeService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    //删除操作
    @RequiredPermission("员工删除")
    public String delete() throws Exception {
        if (employee.getId() != null) {
            employeeService.delete(employee.getId());
            putResponseText("删除成功","html");
        }
        return NONE;
    }

    //进入录入界面
    @RequiredPermission("员工编辑")
    public String input() throws Exception {
        getContext("depts", departmentService.listAll());
        getContext("roles", roleService.listAll());
        if (employee.getId() != null) {
            employee = employeeService.get(this.employee.getId());
        }
        return INPUT;
    }

    //新增或者是更新操作saveOrUpdate
    @RequiredPermission("员工保存或更新")
    public String saveOrUpdate() {
        try {
            //int a = 1 / 0;//模拟异常
            if (employee.getId() == null) {
                employeeService.save(employee);
                addActionMessage("保存成功");
            } else {
                employeeService.update(employee);
                addActionMessage("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作失败");
        }
        return SUCCESS;
    }

    @Setter
    private List<Long> ids;

    //批量删除操作
    @RequiredPermission("批量删除操作")
    public String batchDelete() throws Exception {
        if (ids != null && ids.size() > 0) {
            employeeService.batchDelete(ids);
        }
        return NONE;
    }

    //在SaveOrUpdate之前执行
    public void prepareSaveOrUpdate() throws Exception {
        if (employee.getId() != null) {
            employee = employeeService.get(this.employee.getId());
            employee.setDept(null);
        }
        employee.getRoles().clear();
    }
}
