package mybatis;

import com.wuchao.bean.Emp;
import com.wuchao.dao.EmpMapper;
import com.wuchao.service.EmpService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class MybatisSpring {

    @Autowired
    private EmpService empService;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    private SqlSession sqlSession;

    //@Before
    public void before(){
        System.out.println(sqlSessionFactory);
        sqlSession=sqlSessionFactory.openSession();
        System.out.println(sqlSession);
    }

    @Test
    public void test01(){
        List<Emp> empList = empMapper.getEmpAll();
        for (Emp emp : empList) {
            System.out.println(emp);
        }
    }

    @Test
    public void getEmpByEmpno(){
        Emp emp = empMapper.getEmpByEmpno(7839);
        System.out.println(emp);
    }

    @Test
    public void testTransfor(){
        double transforSal=4001;
        Emp e0=empMapper.getEmpByEmpno(7839);
        e0.setSal(transforSal); //扣

        Emp e1=empMapper.getEmpByEmpno(7788);
        e1.setSal(transforSal);//加

        try {
            boolean f=empService.transfor(e0, e1);
            System.out.println(f);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
