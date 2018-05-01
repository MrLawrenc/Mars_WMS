package com._520it.wms.service;


import com._520it.wms.domain.Client;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface IClientService {
    void save(Client client);

    void update(Client client);

    void delete(Long id);

    Client get(Long id);

    List<Client> listAll();

    //分页查询
    public PageResult query(QueryObject qo);
}
