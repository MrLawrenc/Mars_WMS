package com._520it.wms.query;

import com._520it.wms.domain.Depot;
import javassist.compiler.ast.Keyword;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
//查询的主体是：productStock
public class ProductStockQueryObject extends QueryObject {
    private String keyword;//货品名称
    private Long depotId = -1L;
    private Long brandId = -1L;
    private BigDecimal storeLimit;//库存阈值

    public void customizedQuery() {
        if (hasLength(keyword)) {
            String key = "%" + keyword + "%";
            addQuery("obj.product.name LIKE ?", key);
        }
        if (depotId > 0) {
            addQuery("obj.depot.id=?", depotId);
        }
        if (brandId > 0) {
            addQuery("obj.product.brand.id=?", brandId);
        }
        if (storeLimit != null) {
            addQuery("obj.storeNumber<=?", storeLimit);
        }
        //查询即时库存，肯定是存在库存的，也就是说库存量必须大于0
        addQuery("obj.storeNumber>?", BigDecimal.ZERO);
    }
}
