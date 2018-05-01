package com._520it.wms.service;


import com._520it.wms.domain.Product;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface IProductService {
    void save(Product product);

    void update(Product product);

    void delete(Long id);

    Product get(Long id);

    List<Product> listAll();

    //分页查询
    public PageResult query(QueryObject qo);
}
