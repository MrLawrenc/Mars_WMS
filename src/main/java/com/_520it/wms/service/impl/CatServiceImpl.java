package com._520it.wms.service.impl;

import com._520it.wms.dao.ICatDAO;
import com._520it.wms.domain.Cat;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.ICatService;
import java.util.List;

public class CatServiceImpl implements ICatService{
    @lombok.Setter
    private ICatDAO catDAO;

    @Override
    public void save(Cat cat){
        catDAO.save(cat);
    }

    @Override
    public void update(Cat cat){
        catDAO.update(cat);
    }

    @Override
    public void delete(Long id){
        catDAO.delete(id);
    }

    @Override
    public Cat get(Long id){
        return catDAO.get(id);
    }

    @Override
    public List<Cat> listAll(){
        return catDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo){
        return catDAO.query(qo);
    }
 }
