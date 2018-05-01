package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

//销售帐
@Getter
@Setter
public class SaleAccount extends BaseDomain {
    private Date vdate;//业务时间
    private BigDecimal costPrice;//成本价(库存价格)
    private BigDecimal costAmount;//成本总金额
    private BigDecimal salePrice;//销售价
    private BigDecimal saleAmount;//销售总金额
    private BigDecimal number;//销售数量

    private Product product;//哪一个货品
    private Client client;//客户
    private Employee saleman;//销售人员
}
