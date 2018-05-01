package com._520it.wms.web.action;

import com._520it.wms.query.ProductStockQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IProductStockService;
import com._520it.wms.utils.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

public class ProductStockAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Setter
    private IDepotService depotService;
    @Setter
    private IBrandService brandService;
    @Setter
    private IProductStockService productStockService;


    @Getter
    private ProductStockQueryObject qo = new ProductStockQueryObject();

    @RequiredPermission("即时库存报表")
    public String execute() throws Exception {
        qo.setPageSize(10);
        getContext("depots", depotService.listAll());
        getContext("brands", brandService.listAll());
        getContext("pageResult", productStockService.query(qo));
        return LIST;
    }
}
