package com._520it.wms.web.action;

import com._520it.wms.domain.Client;
import  com._520it.wms.query.ClientQueryObject;
import com._520it.wms.service.IClientService;
import com._520it.wms.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class ClientAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Setter
    private IClientService clientService;
    @Getter
    private Client client = new Client();
    @Getter
    private ClientQueryObject qo = new ClientQueryObject();

    @RequiredPermission("客户列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            getContext("pageResult", clientService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("客户删除")
    public String delete() throws Exception {
        if (client.getId() != null) {
            clientService.delete(client.getId());
            putResponseText("删除成功","html");
        }
        return NONE;
    }
    @RequiredPermission("客户的编辑")
    public String input() throws Exception {
        if (client.getId() != null) {
            client = clientService.get(this.client.getId());
        }
        return INPUT;
    }

    @RequiredPermission("客户的保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (client.getId() == null) {
                clientService.save(client);
                addActionMessage("保存客户信息成功");
            } else {
                clientService.update(client);
                addActionMessage("更改客户信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作失败");
        }
        return SUCCESS;
    }

    //在SaveOrUpdate之前执行
    public void prepareSaveOrUpdate() throws Exception {
        if(client.getId()!=null){
            client=clientService.get(client.getId());
        }
    }
}
