package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

//品牌管理
@Getter
@Setter
public class Brand extends BaseDomain {
    private String name;//品牌名称
    private String sn;//品牌编码
}
