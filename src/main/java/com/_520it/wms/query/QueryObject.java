package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//所有查询对象的父类
public class QueryObject {
    @Getter
    @Setter
    private int currentPage = 1;//当前页
    @Getter
    @Setter
    private int pageSize = 5;//每页大小

    private List<String> conditions = new ArrayList<>();//用来装查询条件
    private List<Object> params = new ArrayList<>();//用来装占位符参数

    private boolean build = false;//表示是否已经拼接了SQL

    //做初始化操作，吧查询条件拼接成SQL或者HQL
    private void init() {
        if (!build) {
            this.customizedQuery();
            build = true;
        }
    }

    public String getQuery() {
        init();
        if (conditions.size() == 0) {
            return "";
        }
        return " WHERE " + StringUtils.join(conditions, " AND ");
    }

    //返回查询条件中占位符对应的常数值
    public List getParameters() {
        init();
        return this.params;
    }

    /*
   * 判断字符串是否有内容
   * */
    protected boolean hasLength(String str) {
        return str != null && !"".equals(str.trim());
    }

    //暴露个子类在该方法中添加查询条件
    protected void customizedQuery() {
    }

    protected void addQuery(String condition, Object... args) {
        this.conditions.add(condition);
        List<Object> list = Arrays.asList(args);//将数组转换成集合
        this.params.addAll(list);
    }
}
