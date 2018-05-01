package com._520it.wms.dao;

import com._520it.wms.query.OrderChartQueryObject;
import com._520it.wms.query.SaleChartQueryObject;
import com._520it.wms.vo.OrderChartVO;
import com._520it.wms.vo.SaleChartVO;

import java.util.List;

//报表查询的DAO
public interface IChartDAO {
    //订货报表查询
    List<OrderChartVO> queryOrderChart(OrderChartQueryObject qo);

    //销售报表查询
    List<SaleChartVO> querySaleChart(SaleChartQueryObject qo);
}
