package com._520it.wms.service;


import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface IStockOutcomeBillService {
    void save(StockOutcomeBill stockIncomeBill);

    void update(StockOutcomeBill stockIncomeBill);

    void delete(Long id);

    StockOutcomeBill get(Long id);

    List<StockOutcomeBill> listAll();

    //分页查询
    public PageResult query(QueryObject qo);

    //销售出库单审核
    void audit(Long id);
}
