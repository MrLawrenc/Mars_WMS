package com._520it.wms.service.impl;

import com._520it.wms.dao.IBrandDAO;
import com._520it.wms.domain.Brand;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IBrandService;
import java.util.List;

public class BrandServiceImpl implements IBrandService{
    @lombok.Setter
    private IBrandDAO brandDAO;

    @Override
    public void save(Brand brand){
        brandDAO.save(brand);
    }

    @Override
    public void update(Brand brand){
        brandDAO.update(brand);
    }

    @Override
    public void delete(Long id){
        brandDAO.delete(id);
    }

    @Override
    public Brand get(Long id){
        return brandDAO.get(id);
    }

    @Override
    public List<Brand> listAll(){
        return brandDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo){
        return brandDAO.query(qo);
    }
 }
