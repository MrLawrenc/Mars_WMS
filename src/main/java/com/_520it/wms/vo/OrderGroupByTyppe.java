package com._520it.wms.vo;

import lombok.Getter;

//订货报表时分组查询的类型
@Getter
public enum OrderGroupByTyppe {
    //枚举常量    每一个枚举常量表示每一个分组情况
    EMPLOYEE("obj.bill.inputUser.name", "obj.bill.inputUser", "订货人员"),
    PRODUCT("obj.product.name", "obj.product", "货品名称"),
    BRAND("obj.product.brand.name", "obj.product.brand", "品牌名称"),
    SUPPLIER("obj.bill.supplier.name", "obj.bill.supplier", "供应商"),
    MONTH("date_format(obj.bill.vdate,'%Y-%m')", "date_format(obj.bill.vdate,'%Y-%m')", "订货日期(月)"),
    DAY("date_format(obj.bill.vdate,'%Y-%m-%d')", "date_format(obj.bill.vdate,'%Y-%m-%d')", "订货日期(日)");

    //缺省默认就是私有的private
    OrderGroupByTyppe(String groupValue, String groupBy, String groupType) {
        this.groupBy = groupBy;
        this.groupType = groupType;
        this.groupValue = groupValue;

    }

    private String groupType;//分组类型：网页上的中文名称
    private String groupValue;//分组名称：hql中的
    private String groupBy;//分组查询：hql中GROUNP BY后面的
}
