package com._520it.wms.web.action;

import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.query.StockOutcomeBillQueryObject;
import com._520it.wms.service.IClientService;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IStockOutcomeBillService;
import com._520it.wms.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class StockOutcomeBillAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Setter
    private IDepotService depotService;
    @Setter
    private IStockOutcomeBillService stockOutcomeBillService;
    @Setter
    private IClientService clientService;
    @Getter
    private StockOutcomeBill stockOutcomeBill = new StockOutcomeBill();
    @Getter
    private StockOutcomeBillQueryObject qo = new StockOutcomeBillQueryObject();

    @RequiredPermission("销售出库单列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            getContext("clients", clientService.listAll());
            getContext("depots", depotService.listAll());
            getContext("pageResult", stockOutcomeBillService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("销售出库单删除")
    public String delete() throws Exception {
        if (stockOutcomeBill.getId() != null) {
            stockOutcomeBillService.delete(stockOutcomeBill.getId());
            putResponseText("删除成功", "html");
        }
        return NONE;
    }

    @RequiredPermission("销售出库单的编辑")
    public String input() throws Exception {
        getContext("depots", depotService.listAll());
        getContext("clients", clientService.listAll());
        if (stockOutcomeBill.getId() != null) {
            stockOutcomeBill = stockOutcomeBillService.get(this.stockOutcomeBill.getId());
        }
        return INPUT;
    }

    @RequiredPermission("销售出库单的保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (stockOutcomeBill.getId() == null) {
                stockOutcomeBillService.save(stockOutcomeBill);
                addActionMessage("保存销售出库单信息成功");
            } else {
                stockOutcomeBillService.update(stockOutcomeBill);
                addActionMessage("更改销售出库单信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作失败");
        }
        return SUCCESS;
    }

    @RequiredPermission("销售出库单审核")
    public String audit() {
        if (stockOutcomeBill.getId() != null) {
            try {
                stockOutcomeBillService.audit(stockOutcomeBill.getId());
            } catch (RuntimeException e) {
                addActionMessage(e.getMessage());
                return SUCCESS;
            }
            addActionMessage("审核成功");
        }
        return SUCCESS;
    }

    //在SaveOrUpdate之前执行
    public void prepareSaveOrUpdate() throws Exception {
        if (stockOutcomeBill.getId() != null) {
            stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill.getId());

            stockOutcomeBill.setDepot(null);
            stockOutcomeBill.setClient(null);
            stockOutcomeBill.getItems().clear();
        }
    }
}
