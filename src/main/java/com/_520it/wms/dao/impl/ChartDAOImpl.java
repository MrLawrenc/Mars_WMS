package com._520it.wms.dao.impl;

import com._520it.wms.dao.IChartDAO;
import com._520it.wms.query.OrderChartQueryObject;
import com._520it.wms.query.QueryObject;
import com._520it.wms.query.SaleChartQueryObject;
import com._520it.wms.vo.OrderChartVO;
import com._520it.wms.vo.OrderGroupByTyppe;
import com._520it.wms.vo.SaleChartVO;
import com._520it.wms.vo.SaleGroupByType;
import lombok.Setter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ChartDAOImpl implements IChartDAO {

    @Setter
    private SessionFactory sessionFactory;

    //销售报表
    public List<SaleChartVO> querySaleChart(SaleChartQueryObject qo) {
        SaleGroupByType groupType = SaleGroupByType.valueOf(qo.getGroupType().toUpperCase());
        Session session = sessionFactory.getCurrentSession();
        StringBuilder hql = new StringBuilder(80);
        hql.append("SELECT NEW com._520it.wms.vo.SaleChartVO(");
        hql.append(groupType.getGroupValue());
        hql.append(",SUM(obj.number),SUM(obj.saleAmount),SUM(obj.saleAmount-obj.costAmount)) FROM SaleAccount obj ");
        hql.append(qo.getQuery());
        hql.append(" GROUP BY ");
        hql.append(groupType.getGroupBy());
        Query query = session.createQuery(hql.toString());
        setPlaceParameters(qo, query);
        return query.list();
    }

    public List<OrderChartVO> queryOrderChart(OrderChartQueryObject qo) {
        /*
        将字符串转换为对应的枚举对象
        * OrderGroupByTyppe.valueOf(qo.getGroupType())
        * */

        //得到枚举对象
        OrderGroupByTyppe groupByTyppe = OrderGroupByTyppe.valueOf(qo.getGroupType().toUpperCase());

        Session session = sessionFactory.getCurrentSession();
        StringBuilder hql = new StringBuilder(80);
        hql.append("SELECT NEW com._520it.wms.vo.OrderChartVO(");
        hql.append(groupByTyppe.getGroupValue());
        hql.append(",SUM(obj.number),SUM(obj.amount)) FROM  OrderBillItem obj ");
        hql.append(qo.getQuery());
        hql.append(" GROUP BY ");
        hql.append(groupByTyppe.getGroupBy());
        Query query = session.createQuery(hql.toString());
        setPlaceParameters(qo, query);
        return query.list();
    }

    //高级查询的设置占位符参数
    private void setPlaceParameters(QueryObject qo, Query query) {
        for (int i = 0; i < qo.getParameters().size(); i++) {
            query.setParameter(i, qo.getParameters().get(i));
        }
    }
}
