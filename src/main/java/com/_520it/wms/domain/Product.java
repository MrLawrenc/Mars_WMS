package com._520it.wms.domain;

import com._520it.wms.utils.FileUploadUtil;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

//货品
@Getter
@Setter
@ToString
public class Product extends BaseDomain {
    private String sn;//编号
    private String name;
    private Brand brand;//品牌
    private BigDecimal costPrice;//成本价
    private BigDecimal salePrice;//销售价
    private String imagePath;//图片存储路径
    private String intro;//货品简介

    //返回缩略图路径
    public String getSmallImagePath() {
        if (imagePath == null) {
            return "";
        }
        int index = imagePath.lastIndexOf(".");
        return imagePath.substring(0, index) + FileUploadUtil.suffix + imagePath.substring(index);
    }

    //返回当前货品对象的JSON数据
    public String getProductJson() {
        Map<String, Object> json = new HashMap<>();
        json.put("id", id);
        json.put("name", name);
        json.put("costPrice", costPrice);
        json.put("salePrice", costPrice);
        json.put("brandName", this.brand != null ? this.getBrand().getName() : null);
        return JSON.toJSONString(json);
    }
}
