package com._520it.generator;

import com._520it.wms.domain.*;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.MessageFormat;

//代码生成器
public class CodeGenerator {
    private static Configuration config;

    static {
        try {
            //创建配置对象
            config = new Configuration();
            //设置模板文件加载目录
            config.setDirectoryForTemplateLoading(new File("templates"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        //generatoCode();
        System.out.println("生成完毕");
    }

    public static void generatoCode() throws Exception {
        ClassInfo info = new ClassInfo(SaleAccount.class);
        //生成DAO模板
        String path = "src\\main\\java\\{0}\\dao" + "/I{1}DAO.java";
        creatFile(info, "IDAO.java", path);
        //生成DAO实现的模板
        creatFile(info, "DAOImpl.java", "src\\main\\java\\{0}\\dao/impl" + "/{1}DAOImpl.java");
        //生成service的模板
        creatFile(info, "IService.java", "src\\main\\java\\{0}\\service" + "/I{1}Service.java");
        //生成service实现的模板
        creatFile(info, "ServiceImpl.java", "src\\main\\java\\{0}\\service/impl" + "/{1}ServiceImpl.java");
        //生成Query的模板
        creatFile(info, "QueryObject.java", "src\\main\\java\\{0}\\query" + "/{1}QueryObject.java");
        //生成Action的模板
        creatFile(info, "Action.java", "src\\main\\java\\{0}\\web/action" + "/{1}Action.java");
        //生成JSP的模板(list.jsp和input.jsp)
        creatFile(info, "list.jsp", "src\\main\\webapp/WEB-INF/views/{2}/list.jsp");
        creatFile(info, "input.jsp", "src\\main\\webapp/WEB-INF/views/{2}/input.jsp");
        //生成映射文件
        creatFile(info, "hbm.xml", "src\\main\\resources/{0}/domain/{1}.hbm.xml");
        //=============追加action dao service的配置文件======================
        appendToXML(info, "dao.xml", "src/main/resources/applicationContext-dao.xml ");
        appendToXML(info, "service.xml", "src/main/resources/applicationContext-service.xml ");
        appendToXML(info, "action.xml", "src/main/resources/applicationContext-action.xml ");

    }

    /*
    * 生成文件
    * @param info 封装的数据对象
    * @param templateName 对应的模板名称
    * @param path 生成的文件路径 需要把.换成/
    * */
    public static void creatFile(ClassInfo info, String templateName, String path) throws Exception {
        Template template = config.getTemplate(templateName);
        String pathfile = MessageFormat.format(path, info.getBasePkg().replace(".", "/"), info.getClassName(), info.getObjName());
        //在生成jsp的时候需要生成list。jsp的父目录
        File file = new File(pathfile);
        if (!file.getParentFile().exists()) {//判断当前jsp文件的父目录是否存在，如果不存在就生成
            file.getParentFile().mkdirs();
        }

        template.process(info, new FileWriter(file));
    }

    /*
    * 在xml文件中插入相应的文本配置
    * @param targetFile 目标文件
    * */
    public static void appendToXML(ClassInfo info, String templateName, String targetFile) throws Exception {
        Template template = config.getTemplate(templateName);
        StringWriter out = new StringWriter();
        template.process(info, out);
        String appendXML = out.toString();//将结果作为字符串流输出（不作为文件流）
        XmlUtil.mergeXML(new File(targetFile), appendXML);//将appendXML插入到targetFile文件中去
    }
}
