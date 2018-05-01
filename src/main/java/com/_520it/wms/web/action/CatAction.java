package com._520it.wms.web.action;

import com._520it.wms.domain.Cat;
import  com._520it.wms.query.CatQueryObject;
import com._520it.wms.service.ICatService;
import com._520it.wms.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class CatAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Setter
    private ICatService catService;
    @Getter
    private Cat cat = new Cat();
    @Getter
    private CatQueryObject qo = new CatQueryObject();

    @RequiredPermission("Cat列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            getContext("pageResult", catService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("Cat删除")
    public String delete() throws Exception {
        if (cat.getId() != null) {
            catService.delete(cat.getId());
            putResponseText("删除成功","html");
        }
        return SUCCESS;
    }
    @RequiredPermission("Cat的编辑")
    public String input() throws Exception {
        if (cat.getId() != null) {
            cat = catService.get(this.cat.getId());
        }
        return INPUT;
    }

    @RequiredPermission("Cat的保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (cat.getId() == null) {
                catService.save(cat);
                addActionMessage("保存Cat信息成功");
            } else {
                catService.update(cat);
                addActionMessage("更改Cat信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作失败");
        }
        return SUCCESS;
    }

    //在SaveOrUpdate之前执行
    public void prepareSaveOrUpdate() throws Exception {
    }
}
