package ${basePkg}.web.action;

import ${basePkg}.domain.${className};
import  ${basePkg}.query.${className}QueryObject;
import ${basePkg}.service.I${className}Service;
import ${basePkg}.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class ${className}Action extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Setter
    private I${className}Service ${objName}Service;
    @Getter
    private ${className} ${objName} = new ${className}();
    @Getter
    private ${className}QueryObject qo = new ${className}QueryObject();

    @RequiredPermission("${className}列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            getContext("pageResult", ${objName}Service.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("${className}删除")
    public String delete() throws Exception {
        if (${objName}.getId() != null) {
            ${objName}Service.delete(${objName}.getId());
            putResponseText("删除成功","html");
        }
        return NONE;
    }
    @RequiredPermission("${className}的编辑")
    public String input() throws Exception {
        if (${objName}.getId() != null) {
            ${objName} = ${objName}Service.get(this.${objName}.getId());
        }
        return INPUT;
    }

    @RequiredPermission("${className}的保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (${objName}.getId() == null) {
                ${objName}Service.save(${objName});
                addActionMessage("保存${className}信息成功");
            } else {
                ${objName}Service.update(${objName});
                addActionMessage("更改${className}信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作失败");
        }
        return SUCCESS;
    }

    //在SaveOrUpdate之前执行
    public void prepareSaveOrUpdate() throws Exception {
        if(${objName}.getId()!=null){
            ${objName}=${objName}Service.get(${objName}.getId());
        }
    }
}
