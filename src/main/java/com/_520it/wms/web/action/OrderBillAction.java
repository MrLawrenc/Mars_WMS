package com._520it.wms.web.action;

import com._520it.wms.domain.OrderBill;
import com._520it.wms.query.OrderBillQueryObject;
import com._520it.wms.service.IOrderBillService;
import com._520it.wms.service.ISupplierService;
import com._520it.wms.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class OrderBillAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Setter
    private IOrderBillService orderBillService;
    @Setter
    private ISupplierService supplierService;
    @Getter
    private OrderBill orderBill = new OrderBill();
    @Getter
    private OrderBillQueryObject qo = new OrderBillQueryObject();

    @RequiredPermission("采购订单列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            getContext("suppliers", supplierService.listAll());
            getContext("pageResult", orderBillService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("采购订单删除")
    public String delete() throws Exception {
        if (orderBill.getId() != null) {
            orderBillService.delete(orderBill.getId());
            putResponseText("删除成功", "html");
        }
        return NONE;
    }

    @RequiredPermission("采购订单的编辑")
    public String input() throws Exception {
        getContext("suppliers", supplierService.listAll());
        if (orderBill.getId() != null) {
            orderBill = orderBillService.get(this.orderBill.getId());
        }
        return INPUT;
    }

    @RequiredPermission("采购订单的保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (orderBill.getId() == null) {
                orderBillService.save(orderBill);
                addActionMessage("保存采购订单信息成功");
            } else {
                orderBillService.update(orderBill);
                addActionMessage("更改采购订单信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作失败");
        }
        return SUCCESS;
    }

    @RequiredPermission("采购订单审核")
    public String audit() {
        if (orderBill.getId() != null) {
            orderBillService.audit(orderBill.getId());
            addActionMessage("审核成功");
        }
        return SUCCESS;
    }

    @RequiredPermission("采购订单的查看")
    public String show() throws Exception {
        if (orderBill.getId() != null) {
            orderBill = orderBillService.get(this.orderBill.getId());
        }
        return "show";
    }

    //在SaveOrUpdate之前执行
    public void prepareSaveOrUpdate() throws Exception {
        if (orderBill.getId() != null) {
            orderBill = orderBillService.get(orderBill.getId());
            orderBill.setSupplier(null);//打破关联关系
            orderBill.getItems().clear();//打破明细关系，成为孤儿
        }
    }
}
