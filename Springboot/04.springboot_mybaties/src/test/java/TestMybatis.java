import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import person.jack.App;
import person.jack.bean.Emp;
import person.jack.dao.EmpMapper;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) /*添加SpringJUnit支持，引入Spring-Test框架*/
@SpringBootTest(classes = App.class) /*指定Springboot启动类启动*/
public class TestMybatis {

    @Autowired
    private EmpMapper empMapper;

    @Test
    public void test(){
        Emp emp = empMapper.selectByPrimaryKey(7788);
        System.out.println(emp);
    }

    @Test
    public void test2(){
        List<Emp> empList = empMapper.selectAll();
        for (Emp emp : empList) {
            System.out.println(emp);
        }
    }
}
