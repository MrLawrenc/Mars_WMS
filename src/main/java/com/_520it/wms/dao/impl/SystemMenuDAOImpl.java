package com._520it.wms.dao.impl;

import com._520it.wms.dao.ISystemMenuDAO;
import com._520it.wms.domain.Role;
import com._520it.wms.domain.SystemMenu;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class SystemMenuDAOImpl extends GenericDAOImpl<SystemMenu> implements ISystemMenuDAO {
    public List<SystemMenu> queryMenusByParentSn(String sn) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT obj FROM SystemMenu obj WHERE obj.parent.sn=?";
        Query query = session.createQuery(hql);
        query.setParameter(0, sn);
        return query.list();
    }

    @Override
    public List<SystemMenu> queryMenusByParentSnAndRole(String sn, List<Role> roles) {
        for (Role role : roles) {
            System.out.println("44444444444");
            System.out.println("======" + role.getName());
        }
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT m FROM Role r JOIN r.menus m WHERE m.parent.sn = ? AND r IN (:roles)";
        Query query = session.createQuery(hql);
        query.setParameter(0, sn);
        query.setParameter("roles", roles);
        System.out.println("=============================");
        return query.list();
    }

    @Override
    public List<SystemMenu> queryChildrenMenus() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT obj FROM SystemMenu obj WHERE obj.parent IS NOT NULL";
        Query query = session.createQuery(hql);
        return query.list();
    }
}
