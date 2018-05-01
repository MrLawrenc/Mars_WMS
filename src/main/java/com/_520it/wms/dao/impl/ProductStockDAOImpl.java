package com._520it.wms.dao.impl;

import com._520it.wms.dao.IProductStockDAO;
import com._520it.wms.domain.ProductStock;
import org.hibernate.Session;

public class ProductStockDAOImpl extends GenericDAOImpl<ProductStock> implements IProductStockDAO {
    @Override
    public ProductStock getByDepotAndProduct(Long depotId, Long productId) {
        Session session = sessionFactory.getCurrentSession();

        return (ProductStock) session.createQuery("SELECT ps FROM ProductStock ps WHERE ps.depot.id=? AND               ps.product.id=?").setParameter(0, depotId).setParameter(1, productId).uniqueResult();
    }
}
