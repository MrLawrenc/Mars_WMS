package com._520it.wms.service.impl;

import com._520it.wms.dao.IProductStockDAO;
import com._520it.wms.dao.ISaleAccountDAO;
import com._520it.wms.dao.IStockOutcomeBillDAO;
import com._520it.wms.dao.impl.SaleAccountDAOImpl;
import com._520it.wms.domain.ProductStock;
import com._520it.wms.domain.SaleAccount;
import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.domain.StockOutcomeBillItem;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IProductStockService;
import com._520it.wms.service.IStockOutcomeBillService;
import com._520it.wms.utils.UserContext;
import lombok.Setter;
import org.apache.struts2.views.freemarker.tags.RadioModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class StockOutcomeBillServiceImpl implements IStockOutcomeBillService {
    @Setter
    private IStockOutcomeBillDAO stockOutcomeBillDAO;
    @Setter
    private IProductStockService productStockService;
    @Setter
    private ISaleAccountDAO saleAccountDAO;

    @Override
    public void save(StockOutcomeBill bill) {
        bill.setInputTime(new Date());
        bill.setInputUser(UserContext.getEmployee());
        bill.setStatus(StockOutcomeBill.NORMAL);

        parseItems(bill);
        stockOutcomeBillDAO.save(bill);
    }

    //处理明细
    private void parseItems(StockOutcomeBill bill) {
        bill.setTotalNumber(BigDecimal.ZERO);
        bill.setTotalAmount(BigDecimal.ZERO);
        for (StockOutcomeBillItem item : bill.getItems()) {
            item.setBill(bill);
            item.setAmount(item.getNumber().multiply(item.getSalePrice()).setScale(2, RoundingMode.HALF_UP));
            bill.setTotalNumber(bill.getTotalNumber().add(item.getNumber()));
            bill.setTotalAmount(bill.getTotalAmount().add(item.getAmount()));
        }
    }

    @Override
    public void update(StockOutcomeBill stockIncomeBill) {
        if (stockIncomeBill.getStatus() == StockOutcomeBill.NORMAL) {
            parseItems(stockIncomeBill);
            stockOutcomeBillDAO.update(stockIncomeBill);
        }
    }

    @Override
    public void delete(Long id) {
        stockOutcomeBillDAO.delete(id);
    }

    @Override
    public StockOutcomeBill get(Long id) {
        return stockOutcomeBillDAO.get(id);
    }

    @Override
    public List<StockOutcomeBill> listAll() {
        return stockOutcomeBillDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return stockOutcomeBillDAO.query(qo);
    }

    public void audit(Long id) {
        StockOutcomeBill bill = stockOutcomeBillDAO.get(id);
        //1.判断为未审核状态
        if (bill.getStatus() == StockOutcomeBill.NORMAL) {
            //2.设置为审核状态，审核人，审核时间
            bill.setStatus(StockOutcomeBill.AUDIT);
            bill.setAuditor(UserContext.getEmployee());
            bill.setAuditTime(new Date());
            //3.库存管理
            for (StockOutcomeBillItem item : bill.getItems()) {
                //库存的出库操作
                BigDecimal costPrice = productStockService.stockOutcome(bill.getDepot(), item);

                //销售帐信息
                SaleAccount sa = new SaleAccount();
                sa.setCostPrice(costPrice);
                sa.setVdate(bill.getVdate());
                sa.setNumber(item.getNumber());
                sa.setCostAmount(sa.getNumber().multiply(sa.getCostPrice()).setScale(2, RoundingMode.HALF_UP));
                sa.setSalePrice(item.getSalePrice());
                sa.setSaleman(bill.getInputUser());
                sa.setSaleAmount(item.getAmount());
                sa.setProduct(item.getProduct());
                sa.setClient(bill.getClient());
                saleAccountDAO.save(sa);
            }

            //4.更改单据状态
            stockOutcomeBillDAO.update(bill);
        }
    }
}
