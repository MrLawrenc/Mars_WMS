package com._520it.wms.query;

import com._520it.wms.domain.OrderBill;
import com._520it.wms.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

//销售报表查询对象 查询主体是SaleAccount
@Setter
@Getter
public class SaleChartQueryObject extends QueryObject {
    private Date beginDate;
    private Date endDate;
    private String keyword;//产品名称
    private Long clientId = -1L;
    private Long brandId = -1L;
    private String groupType = "employee";//分组名称

    @Override
    protected void customizedQuery() {
        if (beginDate != null) {
            addQuery("obj.vdate >= ?", DateUtil.getBeginDate(beginDate));
        }
        if (endDate != null) {
            addQuery("obj.date <= ?", DateUtil.getEndDate(endDate));
        }
        if (clientId > 0) {
            addQuery("obj.client.id=?", clientId);
        }
        if (hasLength(keyword)) {
            String key = "%" + keyword + "%";
            addQuery("obj.product.name LIKE ?", key);
        }
        if (brandId > 0) {
            addQuery("obj.product.brand.id=?", brandId);
        }
    }
}
