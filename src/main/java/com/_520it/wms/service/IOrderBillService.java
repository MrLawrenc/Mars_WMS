package com._520it.wms.service;


import com._520it.wms.domain.OrderBill;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface IOrderBillService {
    void save(OrderBill orderBill);

    void update(OrderBill orderBill);

    void delete(Long id);

    OrderBill get(Long id);

    List<OrderBill> listAll();

    //分页查询
    public PageResult query(QueryObject qo);

    //审核采购订单
    void audit(Long id);
}
