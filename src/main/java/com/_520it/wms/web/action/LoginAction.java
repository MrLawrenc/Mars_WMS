package com._520it.wms.web.action;

import com._520it.wms.service.IEmployeeService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Setter;

public class LoginAction extends BaseAction {
    @Setter
    private String username;
    @Setter
    private String password;
    @Setter
    private IEmployeeService employeeService;

    @InputConfig(resultName = "login")
    public String execute() throws Exception {
        try {
            employeeService.checkLogin(username, password);
        } catch (RuntimeException e) {
            super.addActionError(e.getMessage());
            return LOGIN;
        }

        return SUCCESS;
    }
}
