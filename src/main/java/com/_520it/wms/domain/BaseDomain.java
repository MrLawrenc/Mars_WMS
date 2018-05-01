package com._520it.wms.domain;

//封装了所有domain的共同代码结构
public class BaseDomain {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
