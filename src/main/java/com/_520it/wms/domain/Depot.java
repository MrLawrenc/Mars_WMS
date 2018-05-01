package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

//仓库对象
@Getter
@Setter
public class Depot extends BaseDomain {
    private String name;//仓库名称
    private String address;//仓库地址
}
