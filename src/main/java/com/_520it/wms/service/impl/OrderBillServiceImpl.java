package com._520it.wms.service.impl;

import com._520it.wms.dao.IOrderBillDAO;
import com._520it.wms.domain.OrderBill;
import com._520it.wms.domain.OrderBillItem;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IOrderBillService;
import com._520it.wms.utils.UserContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class OrderBillServiceImpl implements IOrderBillService {
    @Override
    public void audit(Long id) {
        OrderBill bill = orderBillDAO.get(id);
        //判断当前的单据处于未审核状态
        if (bill.getStatus() == OrderBill.NORMAL) {
            //设置已审核状态
            bill.setStatus(OrderBill.AUDIT);
            //设置审核人
            bill.setAuditor(UserContext.getEmployee());
            //设置审核时间
            bill.setAuditTime(new Date());
            //更新
            orderBillDAO.update(bill);
           /* orderBillDAO.update(bill);
           * 可以不用这个更新 会自动更新。由于我们设置数据已经更改一级缓存了，产生了脏数据，在提交事务的时候回自动发送
           * updata语句
           * */
        }

    }

    @lombok.Setter
    private IOrderBillDAO orderBillDAO;

    @Override
    public void save(OrderBill orderBill) {
        //1.设置制单人和时间
        orderBill.setInputUser(UserContext.getEmployee());
        orderBill.setInputTime(new Date());
        //2.重新设置未审核的状态，确保安全
        orderBill.setStatus(OrderBill.NORMAL);

        parseItems(orderBill);
        //6.保存单据
        orderBillDAO.save(orderBill);
    }

    //处理明细
    private void parseItems(OrderBill orderBill) {
        //初始化数据，防止空指针
        orderBill.setTotalNumber(BigDecimal.ZERO);
        orderBill.setTotalAmount(BigDecimal.ZERO);
        orderBill.setTotalAmount(BigDecimal.ZERO);
        //3.处理单据和明细等的关系
        for (OrderBillItem item : orderBill.getItems()) {
            item.setBill(orderBill);
            //4.计算单条明细金额的小计
            item.setAmount(item.getCostPrice().multiply(item.getNumber()).setScale(2, RoundingMode.HALF_UP));
            //5.计算单据的总数量和总金额
            orderBill.setTotalNumber(orderBill.getTotalNumber().add(item.getNumber()));
            orderBill.setTotalAmount(orderBill.getTotalAmount().add(item.getAmount()));
        }
    }

    @Override
    public void update(OrderBill orderBill) {
        //判断当前订单的审核状态
        if (orderBill.getStatus() == 0) {
            parseItems(orderBill);
            orderBillDAO.update(orderBill);
        }
    }

    @Override
    public void delete(Long id) {
        orderBillDAO.delete(id);
    }

    @Override
    public OrderBill get(Long id) {
        return orderBillDAO.get(id);
    }

    @Override
    public List<OrderBill> listAll() {
        return orderBillDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return orderBillDAO.query(qo);
    }
}
