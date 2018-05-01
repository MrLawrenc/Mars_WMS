package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Department extends BaseDomain {
    private String name;
    private String sn;//部门的编码
}
