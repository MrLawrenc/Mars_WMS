import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class FreeMarkDemo {
    public static void main(String[] args) throws Exception {
        //1.创建一个配置对象
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);
        //2.设置从哪个目录加载模板文件
        configuration.setDirectoryForTemplateLoading(new File("templates"));
        //3.获取模板对象
        Template template = configuration.getTemplate("hello.ftl");
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("name", "MrLiu");
        dataModel.put("age", "17");
        //4.合并模板和数据
        Writer out=new FileWriter("test.html");//输出合并之后的数据
        template.process(dataModel,out);
    }
}
