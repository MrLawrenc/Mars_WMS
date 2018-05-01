package com._520it.wms.service;

import com._520it.wms.query.OrderChartQueryObject;
import com._520it.wms.query.SaleChartQueryObject;
import com._520it.wms.vo.OrderChartVO;
import com._520it.wms.vo.SaleChartVO;

import java.util.List;

public interface IChartService {
    //查询订货报表
    public List<OrderChartVO> queryOrderChart(OrderChartQueryObject qo);

    //查询销售报表
    List<SaleChartVO> querySaleChart(SaleChartQueryObject sqo);
}
