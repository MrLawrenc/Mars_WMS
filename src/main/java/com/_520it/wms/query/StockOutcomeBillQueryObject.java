package com._520it.wms.query;

import com._520it.wms.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StockOutcomeBillQueryObject extends QueryObject {
    private Date beginDate;
    private Date endDate;
    private Long depotId = -1L;//仓库
    private  Long clientId=-1L;//客户ID
    private int status = -1;

    public void customizedQuery() {
        if (beginDate != null) {
            addQuery("obj.vdate >= ?", DateUtil.getBeginDate(beginDate));
        }
        if (endDate != null) {
            addQuery("obj.vdate <= ?", DateUtil.getEndDate(endDate));
        }
        if (depotId > 0) {
            addQuery("obj.depot.id=?", depotId);
        }
        if (clientId > 0) {
            addQuery("obj.clientId.id=?", clientId);
        }
        if (status >= 0) {
            addQuery("obj.status=?", status);
        }

    }
}
