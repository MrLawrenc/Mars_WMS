import com._520it.wms.domain.Employee;
import com._520it.wms.service.IEmployeeService;
import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class App {
    @Autowired
    private IEmployeeService service;

    @Test
    public void test() throws Exception {
        String pwd = ConfigTools.encrypt("admin");
        System.out.println(pwd);
    }

    @Test
    public void test2() {
        for (int i = 0; i <10; i++) {
            Employee e = new Employee();
            e.setName("test"+i);
            e.setPassword("1");
            service.save(e);
        }
    }
    @Test
    public void test3() {
        List<Long> list=new ArrayList();
        list.add(26l);
        list.add(25l);
       service.batchDelete(list);
    }

}
