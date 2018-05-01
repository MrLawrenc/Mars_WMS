package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//到货入库单
@Setter
@Getter
public class StockIncomeBill extends BaseDomain {
    public static int NORMAL = 0;//未审核
    public static int AUDIT = 1;//以审核

    private String sn;//单据编号，可以自动生成 也可以手动录入
    private Date vdate;//业务时间
    private int status = StockIncomeBill.NORMAL;//业务的审核状态 缺省是未审核
    private BigDecimal totalNumber;//入库总数量
    private BigDecimal totalAmount;//入库总数量
    private Depot depot;//入库的仓库
    private Employee inputUser;//制单人
    private Date inputTime;//制单时间
    private Employee auditor;//审核人
    private Date auditTime;//审核时间

    private List<StockIncomeBillItem> items = new ArrayList<>();//单据明细
}
