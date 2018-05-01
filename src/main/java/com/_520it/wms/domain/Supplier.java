package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

//供应商
@Getter
@Setter
public class Supplier extends BaseDomain {
    private String name;//供应商的名称
    private String address;//供应商的地址
    private String phone;//供应商的联系电话
}
