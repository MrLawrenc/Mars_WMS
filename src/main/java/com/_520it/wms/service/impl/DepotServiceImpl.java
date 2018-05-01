package com._520it.wms.service.impl;

import com._520it.wms.dao.IDepotDAO;
import com._520it.wms.domain.Depot;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepotService;
import java.util.List;

public class DepotServiceImpl implements IDepotService{
    @lombok.Setter
    private IDepotDAO depotDAO;

    @Override
    public void save(Depot depot){
        depotDAO.save(depot);
    }

    @Override
    public void update(Depot depot){
        depotDAO.update(depot);
    }

    @Override
    public void delete(Long id){
        depotDAO.delete(id);
    }

    @Override
    public Depot get(Long id){
        return depotDAO.get(id);
    }

    @Override
    public List<Depot> listAll(){
        return depotDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo){
        return depotDAO.query(qo);
    }
 }
