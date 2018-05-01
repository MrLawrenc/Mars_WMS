package com._520it.wms.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.ServletActionContext;

//存放action共同的代码结构
public class BaseAction extends ActionSupport implements Preparable {
    //所有action方法执行之前都会执行
    public void prepare() throws Exception {
    }

    protected static final String LIST = "list";

    //留给子类存放数据
    public void getContext(String name, Object value) {
        ActionContext.getContext().put(name, value);
    }

    /*
    * 给客户端做一个响应
    * */
    protected  void putResponseText(String message,String format) throws  Exception{
        ServletActionContext.getResponse().setContentType("text/"+format+";charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(message);
    }
}
