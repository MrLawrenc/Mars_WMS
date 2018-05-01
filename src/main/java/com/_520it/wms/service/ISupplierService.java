package com._520it.wms.service;


import com._520it.wms.domain.Supplier;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface ISupplierService {
    void save(Supplier supplier);

    void update(Supplier supplier);

    void delete(Long id);

    Supplier get(Long id);

    List<Supplier> listAll();

    //分页查询
    public PageResult query(QueryObject qo);
}
