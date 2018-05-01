package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemMenuQueryObject extends QueryObject {

    private Long parentId = -1L;//上一级菜单id
    private Long parentSn;//父菜单的sn

    public void customizedQuery() {
        if (parentId > 0) {
            addQuery("obj.parent.id=? ", parentId);
        } else {
            addQuery("obj.parent.id IS NULL ");
        }
    }
}
