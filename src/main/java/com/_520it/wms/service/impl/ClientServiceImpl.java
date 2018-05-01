package com._520it.wms.service.impl;

import com._520it.wms.dao.IClientDAO;
import com._520it.wms.domain.Client;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IClientService;
import java.util.List;

public class ClientServiceImpl implements IClientService{
    @lombok.Setter
    private IClientDAO clientDAO;

    @Override
    public void save(Client client){
        clientDAO.save(client);
    }

    @Override
    public void update(Client client){
        clientDAO.update(client);
    }

    @Override
    public void delete(Long id){
        clientDAO.delete(id);
    }

    @Override
    public Client get(Long id){
        return clientDAO.get(id);
    }

    @Override
    public List<Client> listAll(){
        return clientDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo){
        return clientDAO.query(qo);
    }
 }
