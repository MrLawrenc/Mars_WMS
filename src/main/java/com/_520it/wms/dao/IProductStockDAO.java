package com._520it.wms.dao;


import com._520it.wms.domain.ProductStock;

public interface IProductStockDAO extends IGenericDAO<ProductStock> {
    //根据货品编号和仓库编号查询某类货品是否存在某个仓库中
    ProductStock getByDepotAndProduct(Long depotId, Long productId);
}
