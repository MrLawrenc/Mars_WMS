package com._520it.wms.query;

import com._520it.wms.domain.OrderBill;
import com._520it.wms.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

//订货报表查询对象 查询主题是OrderbillItem
@Setter
@Getter
public class OrderChartQueryObject extends QueryObject {
    private Date beginDate;
    private Date endDate;
    private String keyword;//产品名称
    private Long supplierId = -1L;
    private Long brandId = -1L;
    private String groupType="employee";//分组名称

    @Override
    protected void customizedQuery() {
        if (beginDate != null) {
            addQuery("obj.bill.vdate >= ?", DateUtil.getBeginDate(beginDate));
        }
        if (endDate != null) {
            addQuery("obj.bill.date <= ?", DateUtil.getEndDate(endDate));
        }
        if (supplierId > 0) {
            addQuery("obj.bill.supplier.id=?", supplierId);
        }
        if (hasLength(keyword)) {
            String key = "%" + keyword + "%";
            addQuery("obj.product.name LIKE ?", key);
        }
        if (brandId > 0) {
            addQuery("obj.product.brand.id=?", brandId);
        }
        //已审核的才能展现
        addQuery("obj.bill.status=?", OrderBill.AUDIT);
    }
}
