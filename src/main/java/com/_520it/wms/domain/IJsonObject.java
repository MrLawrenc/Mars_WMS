package com._520it.wms.domain;
//让domain类来实现，设置把domain中那些属性转换为json格式
public interface IJsonObject {
    Object toJson();
}
