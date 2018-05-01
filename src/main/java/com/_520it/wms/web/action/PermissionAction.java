package com._520it.wms.web.action;

import com._520it.wms.domain.Permission;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.utils.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

public class PermissionAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Setter
    private IPermissionService permissionService;
    @Getter
    private Permission permission = new Permission();
    @Getter
    private QueryObject qo = new QueryObject();

    //查询列表
    @RequiredPermission("权限列表")
    public String execute() throws Exception {
        getContext("pageResult", permissionService.query(qo));
        return LIST;
    }

    //删除操作
    @RequiredPermission("权限删除")
    public String delete() throws Exception {
        if (permission.getId() != null) {
            permissionService.delete(permission.getId());
            putResponseText("删除成功","html");
        }
        return NONE;
    }

    //加在权限
    @RequiredPermission("权限加载")
    public String reload() throws Exception {
        permissionService.reload();
        return NONE;
    }
}
