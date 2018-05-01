package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

//货品库存
@Setter
@Getter
public class ProductStock extends BaseDomain {
    private Product product;
    private Depot depot;
    private BigDecimal storeNumber;
    private BigDecimal price;//库存价格 采用移动加权平均
    private  BigDecimal amount;//库存金额
}
