package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

//客户对象
@Setter
@Getter
public class Client extends BaseDomain {
    private String name;
    private String sn;//客户编码
    private String phone;//客户电话
}
