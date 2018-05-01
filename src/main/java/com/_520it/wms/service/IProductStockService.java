package com._520it.wms.service;


import com._520it.wms.domain.Depot;
import com._520it.wms.domain.ProductStock;
import com._520it.wms.domain.StockIncomeBillItem;
import com._520it.wms.domain.StockOutcomeBillItem;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

import java.math.BigDecimal;
import java.util.List;

public interface IProductStockService {
    void save(ProductStock productStock);

    void update(ProductStock productStock);

    void delete(Long id);

    ProductStock get(Long id);

    List<ProductStock> listAll();

    //分页查询
    public PageResult query(QueryObject qo);

    //入库操作
    void stockIncome(Depot depot, StockIncomeBillItem item);

    //出库操作
    BigDecimal stockOutcome(Depot depot, StockOutcomeBillItem item);
}
