package com._520it.wms.service;


import com._520it.wms.domain.Brand;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface IBrandService {
    void save(Brand brand);

    void update(Brand brand);

    void delete(Long id);

    Brand get(Long id);

    List<Brand> listAll();

    //分页查询
    public PageResult query(QueryObject qo);
}
