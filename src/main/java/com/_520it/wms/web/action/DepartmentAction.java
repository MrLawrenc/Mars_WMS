package com._520it.wms.web.action;

import com._520it.wms.domain.Department;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class DepartmentAction extends BaseAction {
    @Setter
    private IDepartmentService departmentService;
    @Getter
    private Department department = new Department();
    @Getter
    private QueryObject qo = new QueryObject();

    //查询列表
    @RequiredPermission("部门列表")
    @InputConfig(methodName = "input")
    public String execute() {
        try {
            getContext("pageResult", departmentService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    //删除操作
    @RequiredPermission("部门删除")
    public String delete() throws Exception {
        if (department.getId() != null) {
            departmentService.delete(department.getId());
            putResponseText("删除成功","html");
        }
        return SUCCESS;
    }

    //进入录入界面
    @RequiredPermission("部门编辑")
    public String input() throws Exception {
        if (department.getId() != null) {
            department = departmentService.get(this.department.getId());
        }
        return INPUT;
    }

    //新增或者是更新操作saveOrUpdate
    @RequiredPermission("部门保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (department.getId() == null) {
                departmentService.save(department);
                addActionMessage("保存信息成功");
            } else {
                departmentService.update(department);
                addActionMessage("更新信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作失败");
        }
        return SUCCESS;
    }
}
