package com._520it.wms.service;


import com._520it.wms.domain.Cat;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface ICatService {
    void save(Cat cat);

    void update(Cat cat);

    void delete(Long id);

    Cat get(Long id);

    List<Cat> listAll();

    //分页查询
    public PageResult query(QueryObject qo);
}
