package com._520it.wms.web.action;

import com._520it.wms.domain.Role;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.service.IRoleService;
import com._520it.wms.service.ISystemMenuService;
import com._520it.wms.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class RoleAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Setter
    private IRoleService roleService;
    @Getter
    private Role role = new Role();
    @Getter
    private QueryObject qo = new QueryObject();
    @Setter
    private IPermissionService permissionService;
    @Setter
    private ISystemMenuService systemMenuService;

    //查询列表
    @RequiredPermission("角色列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            getContext("pageResult", roleService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    //删除操作
    @RequiredPermission("角色删除")
    public String delete() throws Exception {
        if (role.getId() != null) {
            roleService.delete(role.getId());
            putResponseText("删除成功","html");
        }
        return NONE;
    }

    //进入编辑界面
    @RequiredPermission("角色的编辑")
    public String input() throws Exception {
        getContext("permissions", permissionService.listAll());
        getContext("menus", systemMenuService.queryChildrenMenus());
        if (role.getId() != null) {
            role = roleService.get(this.role.getId());
        }
        return INPUT;
    }

    //新增或者是更新操作saveOrUpdate
    @RequiredPermission("角色的保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (role.getId() == null) {
                roleService.save(role);
                addActionMessage("保存角色信息成功");
            } else {
                roleService.update(role);
                addActionMessage("更改角色信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作失败");
        }
        return SUCCESS;
    }

    //在SaveOrUpdate之前执行
    public void prepareSaveOrUpdate() throws Exception {
        if (role.getId() != null) {
            role = roleService.get(this.role.getId());
        }
        role.getPermissions().clear();//清除第一次传递的权限
        role.getMenus().clear();//清除第一次传递的菜单选项（我们使用的是二次参数拦截器）
    }
}
