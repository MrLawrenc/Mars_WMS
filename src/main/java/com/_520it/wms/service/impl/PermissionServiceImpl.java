package com._520it.wms.service.impl;

import com._520it.wms.dao.IPermissionDAO;
import com._520it.wms.domain.Permission;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.utils.PermissionUtil;
import com._520it.wms.utils.RequiredPermission;
import com._520it.wms.web.action.BaseAction;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.*;

public class PermissionServiceImpl implements IPermissionService, ApplicationContextAware {
    @Setter
    private IPermissionDAO permissionDAO;

    ApplicationContext ctx = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    @Override
    public void delete(Long id) {
        permissionDAO.delete(id);
    }

    @Override
    public List<Permission> listAll() {
        return permissionDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return permissionDAO.query(qo);
    }

    /*
    * 加在权限的操作
    * */
    public void reload() {
        //0.查询出数据库所有的权限表达式
        List<Permission> permissions = permissionDAO.listAll();
        Set<String> expressions = new HashSet<>();//存放数据库中存在的权限表达式
        for (Permission permission : permissions) {
            expressions.add(permission.getExpression());
        }

        //1.扫描所有的BaseAction的子类
        Map<String, BaseAction> beansMap = ctx.getBeansOfType(BaseAction.class);
        Collection<BaseAction> actions = beansMap.values();
        //2.迭代每一个Action类
        for (BaseAction action : actions) {
            //3.迭代每一个Action类中的方法
            Method[] methods = action.getClass().getDeclaredMethods();
            for (Method method : methods) {
                //4.判断当前方法是否有RequiredPermission标签
                RequiredPermission rp = method.getAnnotation(RequiredPermission.class);
                String exp = PermissionUtil.buildExpression(method);//获取该方法对应的权限表达式
                if (!expressions.contains(exp)) {
                    if (rp != null) {
                        String name = rp.value();

                        Permission p = new Permission();
                        p.setExpression(exp);
                        p.setName(name);
                        permissionDAO.save(p);
                    }
                }
            }
        }
    }

}
