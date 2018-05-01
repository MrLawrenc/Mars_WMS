package com._520it.wms.web.action;

import com._520it.wms.domain.Brand;
import com._520it.wms.query.BrandQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class BrandAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Setter
    private IBrandService brandService;
    @Getter
    private Brand brand = new Brand();
    @Getter
    private BrandQueryObject qo = new BrandQueryObject();

    @RequiredPermission("品牌列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            getContext("pageResult", brandService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("品牌删除")
    public String delete() throws Exception {
        if (brand.getId() != null) {
            brandService.delete(brand.getId());
            putResponseText("删除成功", "html");
        }
        return NONE;
    }

    @RequiredPermission("品牌的编辑")
    public String input() throws Exception {
        if (brand.getId() != null) {
            brand = brandService.get(this.brand.getId());
        }
        return INPUT;
    }

    @RequiredPermission("品牌的保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (brand.getId() == null) {
                brandService.save(brand);
                addActionMessage("保存品牌信息成功");
            } else {
                brandService.update(brand);
                addActionMessage("更改品牌信息成功");
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
