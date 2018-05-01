package com._520it.wms.vo;

import lombok.Getter;

//销售报表时分组查询的类型
@Getter
public enum SaleGroupByType {
    //枚举常量    每一个枚举常量表示每一个分组情况
    EMPLOYEE("obj.saleman.name", "obj.saleman", "销售人员"),
    PRODUCT("obj.product.name", "obj.product", "货品名称"),
    BRAND("obj.product.brand.name", "obj.product.brand", "品牌名称"),
    CLIENT("obj.client.name", "obj.client", "客户"),
    MONTH("date_format(obj.vdate,'%Y-%m')", "date_format(obj.vdate,'%Y-%m')", "销售日期(月)"),
    DAY("date_format(obj.vdate,'%Y-%m-%d')", "date_format(obj.vdate,'%Y-%m-%d')", "销售日期(日)");

    //缺省默认就是私有的private
    SaleGroupByType(String groupValue, String groupBy, String groupType) {
        this.groupBy = groupBy;
        this.groupType = groupType;
        this.groupValue = groupValue;

    }

    private String groupType;//分组类型：网页上的中文名称
    private String groupValue;//分组名称：hql中的
    private String groupBy;//分组查询：hql中GROUNP BY后面的
}
