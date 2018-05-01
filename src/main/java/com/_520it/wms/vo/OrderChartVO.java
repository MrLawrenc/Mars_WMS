package com._520it.wms.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

//订货报表的VO
/*
* 1.分组类型
* 2.订货数量
* 3.订货总金额
* */
@Setter
@Getter
public class OrderChartVO {
    private String groupType;
    private BigDecimal totalNumber;
    private BigDecimal totalAmount;

    public OrderChartVO(String groupType, BigDecimal totalNumber, BigDecimal totalAmount) {
        this.groupType = groupType;
        this.totalNumber = totalNumber;
        this.totalAmount = totalAmount;
    }
}
