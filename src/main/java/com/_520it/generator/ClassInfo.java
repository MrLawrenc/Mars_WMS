package com._520it.generator;

import com._520it.wms.domain.BaseDomain;
import lombok.Getter;

import javax.persistence.Id;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

/*
* 表示数据模型对象，封装了模板中占位符数据
* */
@Getter
public class ClassInfo {
    private String basePkg;//基础包名
    private String className;//domain的简单类名
    private String objName;//domain类的对象名称，首字母小写
    private List<String> propNmaes = new ArrayList<>();//当前对象中所有的属性名

    public ClassInfo(Class clz) throws IntrospectionException {
        className = clz.getSimpleName();
        String pkg = clz.getPackage().getName();//com._520it.wms.domain
        basePkg = pkg.substring(0, pkg.lastIndexOf("."));//除去.domain
        objName = className.substring(0, 1).toLowerCase() + className.substring(1);
        //内省机制
        BeanInfo beanInfo = Introspector.getBeanInfo(clz, BaseDomain.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            propNmaes.add(pd.getName());
        }
    }
}
