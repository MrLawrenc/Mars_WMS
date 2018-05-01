package com._520it.wms.web.action;

import com._520it.wms.domain.Supplier;
import  com._520it.wms.query.SupplierQueryObject;
import com._520it.wms.service.ISupplierService;
import com._520it.wms.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class SupplierAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Setter
    private ISupplierService supplierService;
    @Getter
    private Supplier supplier = new Supplier();
    @Getter
    private SupplierQueryObject qo = new SupplierQueryObject();

    @RequiredPermission("供应商列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            getContext("pageResult", supplierService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("供应商删除")
    public String delete() throws Exception {
        if (supplier.getId() != null) {
            supplierService.delete(supplier.getId());
            putResponseText("删除成功","html");
        }
        return NONE;
    }
    @RequiredPermission("供应商的编辑")
    public String input() throws Exception {
        if (supplier.getId() != null) {
            supplier = supplierService.get(this.supplier.getId());
        }
        return INPUT;
    }

    @RequiredPermission("供应商的保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (supplier.getId() == null) {
                supplierService.save(supplier);
                addActionMessage("保存供应商信息成功");
            } else {
                supplierService.update(supplier);
                addActionMessage("更改供应商信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作失败");
        }
        return SUCCESS;
    }

    //在SaveOrUpdate之前执行
    public void prepareSaveOrUpdate() throws Exception {
    }
}
