package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

//采购订单明细
@Setter
@Getter
public class OrderBillItem extends BaseDomain {
    private Product product;//采购货品
    private BigDecimal costPrice;//成本价
    private BigDecimal number;//采购量
    private BigDecimal amount;//明细小计
    private String remark;//备注

    private OrderBill bill;//关联单据对象
}
