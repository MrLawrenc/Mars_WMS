package com._520it.wms.dao.impl;

import com._520it.wms.dao.IGenericDAO;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericDAOImpl<T> implements IGenericDAO<T> {
    protected SessionFactory sessionFactory;
    private Class<T> targetType;

    public GenericDAOImpl() {
        //获取DAO实现类的泛型父类
        ParameterizedType pType = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获取泛型父类中的泛型参数：这里是获取department或者employee
        targetType = (Class<T>) pType.getActualTypeArguments()[0];
    }

    // 注入属性：sessionFactory
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(T obj) {
        Session session = sessionFactory.getCurrentSession();
        session.save(obj);
    }

    public void update(T obj) {
        Session session = sessionFactory.getCurrentSession();
        session.update(obj);
    }

    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.get(targetType, id);
        session.delete(obj);
    }

    public T get(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (T) session.get(targetType, id);
    }

    public List<T> listAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(targetType).list();
    }

    @Override    //分页查询+高级查询
    public PageResult query(QueryObject qo) {
        /*
        * 先查询结果总数
        * */
        String countHql = "SELECT COUNT(obj) FROM " + targetType.getSimpleName() + " obj " + qo.getQuery();
        Query query = sessionFactory.getCurrentSession().createQuery(countHql);
        //设置占位符参数
        setPlaceParameters(qo, query);
        int totalCount = ((Long) query.uniqueResult()).intValue();
        if (totalCount == 0) {
            return PageResult.empty(qo.getPageSize());
        }
        //----------------------------华丽分割选---------------------------------------------
        /*
        * 再查询结果集
        * */
        String resultHql = "SELECT obj FROM " + targetType.getSimpleName() + " obj " + qo.getQuery();
        query = sessionFactory.getCurrentSession().createQuery(resultHql);
        //设置占位符参数
        setPlaceParameters(qo, query);
        //分页：
        if (qo.getCurrentPage() > 0 && qo.getPageSize() > 0) {
            query.setFirstResult((qo.getCurrentPage() - 1) * qo.getPageSize());
            query.setMaxResults(qo.getPageSize());
        }
        List result = query.list();
        return new PageResult(result, totalCount, qo.getCurrentPage(), qo.getPageSize());
    }


    //高级查询的设置占位符参数
    private void setPlaceParameters(QueryObject qo, Query query) {
        for (int i = 0; i < qo.getParameters().size(); i++) {
            query.setParameter(i, qo.getParameters().get(i));
        }
    }

    @Override
    public List<T> queryForList(String condition, Object[] params, int currentPage, int pageSize) {
        Session session = sessionFactory.getCurrentSession();
        StringBuilder hql = new StringBuilder(80);
        hql.append("SELECT obj FROM ");
        hql.append(targetType.getSimpleName()).append(" obj  ");
        if (params != null && params.length > 0) {
            hql.append(" WHERE ").append(condition);
        }
        Query query = session.createQuery(hql.toString());
        //设置占位符参数
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
        //设置分页
        if (currentPage > 0 && pageSize > 0) {
            query.setFirstResult((currentPage - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        List list = query.list();
        return list;
    }

    @Override
    public List<T> queryForList(String condition, Object... params) {
        return queryForList(condition, params, 0, 0);

    }

    @Override
    public T queryObject(String condition, Object... params) {
        List<T> list = queryForList(condition, params);
        return list.size() == 1 ? list.get(0) : null;
    }

    @Override
    public void batchDelete(List<Long> ids) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "DELETE FROM "+targetType.getSimpleName()+" obj WHERE obj.id IN (:idss)";
        Query query = session.createQuery(hql);
        query.setParameterList("idss", ids);
        query.executeUpdate();
    }
}
