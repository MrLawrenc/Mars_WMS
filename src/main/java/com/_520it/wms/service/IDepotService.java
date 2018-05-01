package com._520it.wms.service;


import com._520it.wms.domain.Depot;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface IDepotService {
    void save(Depot depot);

    void update(Depot depot);

    void delete(Long id);

    Depot get(Long id);

    List<Depot> listAll();

    //分页查询
    public PageResult query(QueryObject qo);
}
