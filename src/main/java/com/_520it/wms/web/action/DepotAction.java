package com._520it.wms.web.action;

import com._520it.wms.domain.Depot;
import  com._520it.wms.query.DepotQueryObject;
import com._520it.wms.service.IDepotService;
import com._520it.wms.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class DepotAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Setter
    private IDepotService depotService;
    @Getter
    private Depot depot = new Depot();
    @Getter
    private DepotQueryObject qo = new DepotQueryObject();

    @RequiredPermission("仓库列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            getContext("pageResult", depotService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("仓库删除")
    public String delete() throws Exception {
        if (depot.getId() != null) {
            depotService.delete(depot.getId());
            putResponseText("删除成功","html");
        }
        return NONE;
    }
    @RequiredPermission("仓库的编辑")
    public String input() throws Exception {
        if (depot.getId() != null) {
            depot = depotService.get(this.depot.getId());
        }
        return INPUT;
    }

    @RequiredPermission("仓库的保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (depot.getId() == null) {
                depotService.save(depot);
                addActionMessage("保存仓库信息成功");
            } else {
                depotService.update(depot);
                addActionMessage("更改仓库信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作失败");
        }
        return SUCCESS;
    }

    //在SaveOrUpdate之前执行
    public void prepareSaveOrUpdate() throws Exception {
        if(depot.getId()!=null){
            depot=depotService.get(depot.getId());
        }
    }
}
