package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductQueryObject extends QueryObject {
    private String keyword;//查询货品名或编号 或其包含的关键字
    private Long brandId = -1L;//品牌查询

    public void customizedQuery() {
        if (hasLength(keyword)) {
            String key = "%" + keyword + "%";
            addQuery("(obj.name LIKE ? OR obj.sn LIKE ?)", key, key);
        }
        if (brandId > 0) {
            addQuery("obj.brand.id = ?", brandId);
        }
    }
}
