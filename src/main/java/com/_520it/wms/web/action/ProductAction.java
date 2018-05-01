package com._520it.wms.web.action;

import com._520it.wms.domain.Product;
import com._520it.wms.query.ProductQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IProductService;
import com._520it.wms.utils.FileUploadUtil;
import com._520it.wms.utils.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

public class ProductAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Setter
    private IBrandService brandService;
    @Setter
    private IProductService productService;
    @Getter
    private Product product = new Product();
    @Getter
    private ProductQueryObject qo = new ProductQueryObject();
    @Setter
    private File pic;//上传的文件
    @Setter
    private String picFileName;//上传文件的原始名称

    @RequiredPermission("货品列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            getContext("brands", brandService.listAll());
            getContext("pageResult", productService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("货品删除")
    public String delete() throws Exception {
        if (product.getId() != null) {
            Product product = productService.get(this.product.getId());
            if (product.getImagePath() != null) {
                FileUploadUtil.deleteFile(product.getImagePath());
            }
            productService.delete(this.product.getId());
            putResponseText("删除成功", "html");
        }
        return NONE;
    }

    @RequiredPermission("货品的编辑")
    public String input() throws Exception {
        getContext("brands", brandService.listAll());
        if (product.getId() != null) {
            product = productService.get(product.getId());
        }
        return INPUT;
    }

    @RequiredPermission("货品的保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            //在更新操作时，需要删除原来的图片
            if (product.getId() != null && pic != null) {
                FileUploadUtil.deleteFile(product.getImagePath());
            }

            if (pic != null) {
                //返回图片保存的路径
                String savePath = FileUploadUtil.uploadFile(pic, picFileName);
                product.setImagePath(savePath);
            }
            if (product.getId() == null) {
                productService.save(product);
                addActionMessage("保存货品信息成功");
            } else {
                productService.update(product);
                addActionMessage("更改货品信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作失败");
        }
        return SUCCESS;
    }

    @RequiredPermission("货品选择列表")
    public String selectProductList() throws Exception {
        getContext("brands", brandService.listAll());
        getContext("pageResult", productService.query(qo));
        return "selectProductList";
    }

    //在SaveOrUpdate之前执行
    public void prepareSaveOrUpdate() throws Exception {
        if (product.getId() != null) {
            product = productService.get(product.getId());
            product.setBrand(null);
        }
    }
}
