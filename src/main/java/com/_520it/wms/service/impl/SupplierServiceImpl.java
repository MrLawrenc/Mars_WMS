package com._520it.wms.service.impl;

import com._520it.wms.dao.ISupplierDAO;
import com._520it.wms.domain.Supplier;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.ISupplierService;
import java.util.List;

public class SupplierServiceImpl implements ISupplierService{
    @lombok.Setter
    private ISupplierDAO supplierDAO;

    @Override
    public void save(Supplier supplier){
        supplierDAO.save(supplier);
    }

    @Override
    public void update(Supplier supplier){
        supplierDAO.update(supplier);
    }

    @Override
    public void delete(Long id){
        supplierDAO.delete(id);
    }

    @Override
    public Supplier get(Long id){
        return supplierDAO.get(id);
    }

    @Override
    public List<Supplier> listAll(){
        return supplierDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo){
        return supplierDAO.query(qo);
    }
 }
