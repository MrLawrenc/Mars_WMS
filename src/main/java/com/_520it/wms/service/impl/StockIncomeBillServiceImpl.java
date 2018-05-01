package com._520it.wms.service.impl;

import com._520it.wms.dao.IProductStockDAO;
import com._520it.wms.dao.IStockIncomeBillDAO;
import com._520it.wms.domain.ProductStock;
import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.domain.StockIncomeBillItem;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IProductStockService;
import com._520it.wms.service.IStockIncomeBillService;
import com._520it.wms.utils.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class StockIncomeBillServiceImpl implements IStockIncomeBillService {
    @lombok.Setter
    private IStockIncomeBillDAO stockIncomeBillDAO;
    @Setter
    private IProductStockService productStockService;

    @Override
    public void save(StockIncomeBill bill) {
        bill.setInputTime(new Date());
        bill.setInputUser(UserContext.getEmployee());
        bill.setStatus(StockIncomeBill.NORMAL);

        parseItems(bill);
        stockIncomeBillDAO.save(bill);
    }

    //处理明细
    private void parseItems(StockIncomeBill bill) {
        bill.setTotalNumber(BigDecimal.ZERO);
        bill.setTotalAmount(BigDecimal.ZERO);
        for (StockIncomeBillItem item : bill.getItems()) {
            item.setBill(bill);
            item.setAmount(item.getNumber().multiply(item.getCostPrice()).setScale(2, RoundingMode.HALF_UP));
            bill.setTotalNumber(bill.getTotalNumber().add(item.getNumber()));
            bill.setTotalAmount(bill.getTotalAmount().add(item.getAmount()));
        }
    }

    @Override
    public void update(StockIncomeBill stockIncomeBill) {
        if (stockIncomeBill.getStatus() == StockIncomeBill.NORMAL) {
            parseItems(stockIncomeBill);
            stockIncomeBillDAO.update(stockIncomeBill);
        }
    }

    @Override
    public void delete(Long id) {
        stockIncomeBillDAO.delete(id);
    }

    @Override
    public StockIncomeBill get(Long id) {
        return stockIncomeBillDAO.get(id);
    }

    @Override
    public List<StockIncomeBill> listAll() {
        return stockIncomeBillDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return stockIncomeBillDAO.query(qo);
    }

    //到货入库单审核之后到货入库单中的每一条明细都要存入仓库中
    public void audit(Long id) {
        StockIncomeBill bill = stockIncomeBillDAO.get(id);
        //1.判断单据处于未审核状态
        if (bill.getStatus() == StockIncomeBill.NORMAL) {
            //2.设置审核人，时间，审核状态
            bill.setAuditor(UserContext.getEmployee());
            bill.setAuditTime(new Date());
            bill.setStatus(StockIncomeBill.AUDIT);
            //3.操作库存
            for (StockIncomeBillItem item : bill.getItems()) {
                productStockService.stockIncome(bill.getDepot(),item);//库存的入库操作

            }
            // 4.更新
        }
    }
}
