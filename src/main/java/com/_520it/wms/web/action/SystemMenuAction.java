package com._520it.wms.web.action;

import com._520it.wms.domain.SystemMenu;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.SystemMenuQueryObject;
import com._520it.wms.service.ISystemMenuService;
import com._520it.wms.utils.RequiredPermission;
import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.sun.tools.jdi.EventSetImpl;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import javax.lang.model.SourceVersion;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SystemMenuAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Setter
    private ISystemMenuService systemMenuService;
    @Getter
    private SystemMenu systemMenu = new SystemMenu();
    @Getter
    private SystemMenuQueryObject qo = new SystemMenuQueryObject();

    @RequiredPermission("系统菜单")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            if (qo.getParentId() > 0) {
                getContext("menus", systemMenuService.queryMenusByParentId(qo.getParentId()));

            }
            getContext("pageResult", systemMenuService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("系统菜单删除")
    public String delete() throws Exception {
        try {
            if (systemMenu.getId() != null) {
                systemMenuService.delete(systemMenu.getId());
                putResponseText("删除成功", "html");
            }
        } catch (Exception e) {
            e.printStackTrace();
            putResponseText(e.getMessage(), "html");
        }
        return NONE;
    }

    @RequiredPermission("系统菜单的编辑")
    public String input() throws Exception {
        if (qo.getParentId() < 0) {
            getContext("parentName", "根菜单");
        } else {
            getContext("parentName", systemMenuService.get(qo.getParentId()).getName());
        }


        if (systemMenu.getId() != null) {
            systemMenu = systemMenuService.get(this.systemMenu.getId());
        }
        return INPUT;
    }


    @RequiredPermission("系统菜单的保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (qo.getParentId() > 0) {
                SystemMenu parent = systemMenuService.get(qo.getParentId());
                systemMenu.setParent(parent);//设置父级菜单
            }


            if (systemMenu.getId() == null) {
                systemMenuService.save(systemMenu);
                addActionMessage("保存系统菜单信息成功");
            } else {
                systemMenuService.update(systemMenu);
                addActionMessage("更改系统菜单信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作失败");
        }
        return SUCCESS;
    }

    //接收zTree发出的异步请求
    @Setter
    private String sn;

    public String loadMenusByParentSn() throws Exception {
        List<SystemMenu> childMenus = systemMenuService.queryMenusByParentSn(sn);
        List<Object> jsonList = new ArrayList<>();//用来封装childMenu转换为json之后的数据
        for (SystemMenu childMenu : childMenus) {
            jsonList.add(childMenu.toJson());
        }
        putResponseText(JSON.toJSONString(jsonList), "json");
        return NONE;
    }

    //在SaveOrUpdate之前执行
    public void prepareSaveOrUpdate() throws Exception {
    }
}
