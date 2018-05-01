package com._520it.wms.service.impl;

import com._520it.wms.dao.IProductStockDAO;
import com._520it.wms.domain.Depot;
import com._520it.wms.domain.ProductStock;
import com._520it.wms.domain.StockIncomeBillItem;
import com._520it.wms.domain.StockOutcomeBillItem;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IProductStockService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ProductStockServiceImpl implements IProductStockService {
    @lombok.Setter
    private IProductStockDAO productStockDAO;

    @Override
    public BigDecimal stockOutcome(Depot depot, StockOutcomeBillItem item) {
        //3.1查询库存是否存在且足够
        ProductStock ps = productStockDAO.getByDepotAndProduct(depot.getId(), item.getProduct().getId());
        if (ps == null || ps.getStoreNumber().compareTo(item.getNumber()) < 0) {
            throw new RuntimeException("对不起，" + item.getProduct().getName() + "库存不足" + item.getNumber() + "!");
        }
        ps.setStoreNumber(ps.getStoreNumber().subtract(item.getNumber()));
        //销售出库不会影响库存价格的变动
        ps.setAmount(ps.getPrice().multiply(ps.getStoreNumber()));

        return ps.getPrice();
    }

    @Override
    public void stockIncome(Depot depot, StockIncomeBillItem item) {
        //3.1：判断某货品是否在耨个仓库中，是否有库存
        ProductStock ps = productStockDAO.getByDepotAndProduct(depot.getId(), item.getProduct().getId());
        if (ps != null) {
            //3.2：如果有库存
            //3.2.1：重新计算库存数量，金额，价格
            ps.setStoreNumber(ps.getStoreNumber().add(item.getNumber()));
            ps.setAmount(ps.getAmount().add(item.getAmount()));
            ps.setPrice(ps.getAmount().divide(ps.getStoreNumber(), 2, RoundingMode.HALF_UP));
            //3.2.2：
            productStockDAO.update(ps);
        } else {
            ps = new ProductStock();
            //3.3.1：如果没库存,创建库存对象，设置仓库，货品编号，计算库存数量，金额，价格
            ps.setDepot(depot);
            ps.setProduct(item.getProduct());
            ps.setStoreNumber(item.getNumber());
            ps.setPrice(item.getCostPrice());
            ps.setAmount(item.getAmount());
            //3.3.2：保存
            productStockDAO.save(ps);
        }
    }

    @Override
    public void save(ProductStock productStock) {
        productStockDAO.save(productStock);
    }

    @Override
    public void update(ProductStock productStock) {
        productStockDAO.update(productStock);
    }

    @Override
    public void delete(Long id) {
        productStockDAO.delete(id);
    }

    @Override
    public ProductStock get(Long id) {
        return productStockDAO.get(id);
    }

    @Override
    public List<ProductStock> listAll() {
        return productStockDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return productStockDAO.query(qo);
    }
}
