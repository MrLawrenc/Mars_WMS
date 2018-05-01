package com._520it.wms.service.impl;

import com._520it.wms.dao.ISystemMenuDAO;
import com._520it.wms.domain.SystemMenu;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.query.SystemMenuQueryObject;
import com._520it.wms.service.ISystemMenuService;
import com._520it.wms.utils.UserContext;
import com._520it.wms.vo.SystemMenuVO;
import sun.util.resources.cldr.ebu.CurrencyNames_ebu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SystemMenuServiceImpl implements ISystemMenuService {
    public List<SystemMenu> queryMenusByParentSn(String sn) {
            return systemMenuDAO.queryMenusByParentSn(sn);
            //====================关于菜单的权限问题=======有bug
       /* if (UserContext.getEmployee().isAdmin()) {//超级管理员
        } else {
            return systemMenuDAO.queryMenusByParentSnAndRole(sn, UserContext.getEmployee().getRoles());
        }*/
    }

    @lombok.Setter
    private ISystemMenuDAO systemMenuDAO;

    @Override
    public void save(SystemMenu systemMenu) {
        systemMenuDAO.save(systemMenu);
    }

    @Override
    public void update(SystemMenu systemMenu) {
        systemMenuDAO.update(systemMenu);
    }

    @Override
    public void delete(Long id) {
        SystemMenuQueryObject qo = new SystemMenuQueryObject();
        qo.setParentId(id);
        PageResult result = systemMenuDAO.query(qo);
        System.out.println("aaaaaa");
        if (result.getTotalCount() > 0) {
            throw new RuntimeException("该对象正在被使用");
        }
        systemMenuDAO.delete(id);
    }

    @Override
    public SystemMenu get(Long id) {
        return systemMenuDAO.get(id);
    }

    @Override
    public List<SystemMenu> listAll() {
        return systemMenuDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return systemMenuDAO.query(qo);
    }

    @Override
    public List<SystemMenuVO> queryMenusByParentId(Long parentId) {
        List<SystemMenuVO> menus = new ArrayList<>();
        //查询出当前子菜单的父级菜单
        SystemMenu currentParent = systemMenuDAO.get(parentId);

        listParents(menus, currentParent);
        Collections.reverse(menus);//颠倒集合中的顺序
        return menus;
    }

    @Override
    public List<SystemMenu> queryChildrenMenus() {
        return systemMenuDAO.queryChildrenMenus();
    }

    /*
    * 递归
    * */
    private void listParents(List<SystemMenuVO> menus, SystemMenu currentParent) {
        if (currentParent != null) {
            SystemMenuVO vo = new SystemMenuVO();
            vo.setId(currentParent.getId());
            vo.setName(currentParent.getName());
            menus.add(vo);
            listParents(menus, currentParent.getParent());
        }
    }

}
