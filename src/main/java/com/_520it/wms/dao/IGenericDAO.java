package com._520it.wms.dao;


import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

/*
* 泛型DAO接口：所有DAO接口的父类接口
* */
public interface IGenericDAO<T> {
    void save(T obj);

    void update(T obj);

    void delete(Long id);

    T get(Long id);

    List<T> listAll();

    //分页查询+高级查询
    PageResult query(QueryObject qo);

    /*
    * 查询符合条件的多条数据
    * @params condition:传入的查询条件，如：obj.name LIKE ? AND Obj.age>?
    * @params params:查询条件的占位符参数
    * @params currentPage:传入的数据--表示当前页显示多少条数据
    * @params pageSIze:传入的数据--表示页面大小
    * */
    List<T> queryForList(String condition, Object[] params, int currentPage, int pageSize);

    List<T> queryForList(String condition, Object... params);

    //查询符合条件的单条数据
    T queryObject(String condition, Object... params);

    //批量删除
    void batchDelete(List<Long> ids);
}
