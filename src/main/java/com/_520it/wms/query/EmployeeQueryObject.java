package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;


/**
 * 员工的高级查询对象：包含了高级查询的所有信息
 */

public class EmployeeQueryObject extends QueryObject {
    @Getter
    @Setter
    private String keyword;//查询邮箱或者名称
    @Getter
    @Setter
    private Long deptId = -1L;//所在部门编号

    //=========================================


    //=========================================

    //返回带有查询条件的hql 如：WHERE (obj.name LIKE ? OR obj.email LIKE ? ) AND obj.dept.id=?
    public void customizedQuery() {
        if (hasLength(keyword)) {
            String key = "%" + keyword + "%";
            super.addQuery("(obj.name LIKE ? OR obj.email LIKE ?) ", key, key);
        }
        if (deptId > 0) {
            super.addQuery("obj.dept.id=?", deptId);
        }
    }
}
