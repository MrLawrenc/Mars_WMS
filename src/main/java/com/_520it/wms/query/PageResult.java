package com._520it.wms.query;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

/*
* 封装分页的查询结果的信息
* */
@Getter
public class PageResult {
    //====SQL查询得出的===============
    private List result;//结果集
    private int totalCount;//结果总条数
    //=========用户传入的数据=========
    private int currentPage = 1;//当前页
    private int pageSize = 3;//每页显示多少条数据
    //=========计算得出的数据=========
    private int totalPage;//总页数
    private int prevPage;//上一页
    private int nextPage;//下一页

    public static PageResult empty(int pageSize) {
        return new PageResult(Collections.emptyList(), 0, 1, pageSize);
    }

    public PageResult(List result, int totalCount, int currentPage, int pageSize) {
        this.result = result;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;

        this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        this.prevPage = currentPage - 1 > 0 ? currentPage - 1 : 1;
        this.nextPage = currentPage + 1 <= totalPage ? currentPage + 1 : totalPage;
    }
}
