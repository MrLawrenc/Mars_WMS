package com._520it.wms.service.impl;

import com._520it.wms.dao.IProductDAO;
import com._520it.wms.domain.Product;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IProductService;
import java.util.List;

public class ProductServiceImpl implements IProductService{
    @lombok.Setter
    private IProductDAO productDAO;

    @Override
    public void save(Product product){
        productDAO.save(product);
    }

    @Override
    public void update(Product product){
        productDAO.update(product);
    }

    @Override
    public void delete(Long id){
        productDAO.delete(id);
    }

    @Override
    public Product get(Long id){
        return productDAO.get(id);
    }

    @Override
    public List<Product> listAll(){
        return productDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo){
        return productDAO.query(qo);
    }
 }
