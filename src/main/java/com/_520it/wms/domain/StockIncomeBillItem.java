package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

//到货入库单明细
@Setter
@Getter
public class StockIncomeBillItem extends BaseDomain {
    private BigDecimal costPrice;//入库的价
    private BigDecimal number;//入库的数量
    private BigDecimal amount;//明细小计
    private String remark;//备注
    private Product product;//货品

    private StockIncomeBill bill;//关联入库单对象
}
