package com._520it.wms.web.action;

import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.query.StockIncomeBillQueryObject;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IStockIncomeBillService;
import com._520it.wms.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class StockIncomeBillAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Setter
    private IDepotService depotService;
    @Setter
    private IStockIncomeBillService stockIncomeBillService;
    @Getter
    private StockIncomeBill stockIncomeBill = new StockIncomeBill();
    @Getter
    private StockIncomeBillQueryObject qo = new StockIncomeBillQueryObject();

    @RequiredPermission("到货入库单列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            getContext("depots", depotService.listAll());
            getContext("pageResult", stockIncomeBillService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("到货入库单删除")
    public String delete() throws Exception {
        if (stockIncomeBill.getId() != null) {
            stockIncomeBillService.delete(stockIncomeBill.getId());
            putResponseText("删除成功", "html");
        }
        return NONE;
    }

    @RequiredPermission("到货入库单的编辑")
    public String input() throws Exception {
        getContext("depots", depotService.listAll());
        if (stockIncomeBill.getId() != null) {
            stockIncomeBill = stockIncomeBillService.get(this.stockIncomeBill.getId());
        }
        return INPUT;
    }

    @RequiredPermission("到货入库单的保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (stockIncomeBill.getId() == null) {
                stockIncomeBillService.save(stockIncomeBill);
                addActionMessage("保存到货入库单信息成功");
            } else {
                stockIncomeBillService.update(stockIncomeBill);
                addActionMessage("更改到货入库单信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作失败");
        }
        return SUCCESS;
    }

    @RequiredPermission("到货入库单审核")
    public String audit() {
        if (stockIncomeBill.getId() != null) {
            stockIncomeBillService.audit(stockIncomeBill.getId());
            addActionMessage("审核成功");
        }
        return SUCCESS;
    }

    //在SaveOrUpdate之前执行
    public void prepareSaveOrUpdate() throws Exception {
        if (stockIncomeBill.getId() != null) {
            stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());

            stockIncomeBill.setDepot(null);
            stockIncomeBill.getItems().clear();
        }
    }
}
