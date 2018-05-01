package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

//销售出库明细
@Setter
@Getter
public class StockOutcomeBillItem extends BaseDomain {
    private BigDecimal salePrice;//销售出库的价
    private BigDecimal number;//销售出库的数量
    private BigDecimal amount;//明细小计
    private String remark;//备注
    private Product product;//货品

    private StockOutcomeBill bill;//关联销售出库单对象
}
