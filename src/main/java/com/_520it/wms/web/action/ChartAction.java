package com._520it.wms.web.action;

import com._520it.wms.query.OrderChartQueryObject;
import com._520it.wms.query.SaleChartQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IChartService;
import com._520it.wms.service.IClientService;
import com._520it.wms.service.ISupplierService;
import com._520it.wms.utils.RequiredPermission;
import com._520it.wms.vo.OrderChartVO;
import com._520it.wms.vo.OrderGroupByTyppe;
import com._520it.wms.vo.SaleChartVO;
import com._520it.wms.vo.SaleGroupByType;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ChartAction extends BaseAction {
    @Setter
    private IChartService chartService;
    @Setter
    private IBrandService brandService;
    @Setter
    private IClientService clientService;
    @Setter
    private ISupplierService supplierService;
    @Getter
    OrderChartQueryObject oqo = new OrderChartQueryObject();
    @Getter
    SaleChartQueryObject sqo = new SaleChartQueryObject();

    //订货报表
    @RequiredPermission("订货报表")
    public String orderChart() throws Exception {
        getContext("orderCharts", chartService.queryOrderChart(oqo));
        getContext("brands", brandService.listAll());
        getContext("suppliers", supplierService.listAll());
        //枚举常量全部传到页面中
        getContext("orderGroupByTyppes", OrderGroupByTyppe.values());
        return "orderChart";
    }

    @RequiredPermission("销售报表")
    public String saleChart() throws Exception {
        getContext("saleCharts", chartService.querySaleChart(sqo));
        getContext("brands", brandService.listAll());
        getContext("clients", clientService.listAll());
        //枚举常量全部传到页面中
        getContext("saleGroupByTypes", SaleGroupByType.values());
        return "saleChart";
    }

    //销售线形图表
    public String saleChartByLine() throws Exception {
        List<SaleChartVO> vos = chartService.querySaleChart(sqo);
        List<String> groupType = new ArrayList<>();
        List<BigDecimal> totalAmount = new ArrayList<>();
        for (SaleChartVO vo : vos) {
            groupType.add(vo.getGroupType());
            totalAmount.add(vo.getTotalAmount());
        }
        getContext("groupBy", SaleGroupByType.valueOf(sqo.getGroupType().toUpperCase()).getGroupType());
        getContext("groupTypes", JSON.toJSONString(groupType));
        getContext("totalAmounts", JSON.toJSONString(totalAmount));

        return "saleChartByLine";
    }

    //销售饼形图表
    public String saleChartByPie() throws Exception {
        List<SaleChartVO> vos = chartService.querySaleChart(sqo);
        List<Object> dates = new ArrayList<>();//饼图所需要的数据
        for (SaleChartVO vo : vos) {
            dates.add(new Object[]{vo.getGroupType(), vo.getTotalAmount()});
        }
        getContext("dates", JSON.toJSONString(dates));
        getContext("groupBy", SaleGroupByType.valueOf(sqo.getGroupType().toUpperCase()).getGroupType());
        return "saleChartByPie";
    }
}
