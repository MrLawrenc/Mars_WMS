package com._520it.wms.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

//销售报表的VO
/*
* 1.分组类型
* 2.订货数量
* 3.订货总金额
* */
@Setter
@Getter
public class SaleChartVO {
    private String groupType;
    private BigDecimal totalNumber;
    private BigDecimal totalAmount;
    private BigDecimal grossProfit;//毛利润

    public SaleChartVO(String groupType, BigDecimal totalNumber, BigDecimal totalAmount, BigDecimal grossProfit) {
        this.groupType = groupType;
        this.totalNumber = totalNumber;
        this.totalAmount = totalAmount;
        this.grossProfit = grossProfit;
    }
}
