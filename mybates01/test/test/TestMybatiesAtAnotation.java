package test;

import com.wuchao.bean.Emp;
import com.wuchao.bean.IEmpMapper;
import com.wuchao.bean.IOrdersMapper;
import com.wuchao.bean.Orders;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class TestMybatiesAtAnotation {
    SqlSessionFactory sqlSessionFactory;
    SqlSession session;

    {
        try {
            //xml 文件字符流
            Reader reader = Resources.getResourceAsReader("mybaties.xml");
            //构建sqlSession 工厂
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAdd() {
        session = sqlSessionFactory.openSession(true);

        //准备对象（参数）
        Emp emp = new Emp();
        emp.setEmpno(1234);
        emp.setEname("悟空");
        emp.setSal(1000);
        emp.setJob("保镖");

        IEmpMapper mapper = session.getMapper(IEmpMapper.class);

        int a=mapper.addEmp(emp);
        System.out.println("受影响："+a);
    }

    @Test //delete 操作
    public void testDelete() {
        session = sqlSessionFactory.openSession(true);
        IEmpMapper empMapper=session.getMapper(IEmpMapper.class);
        int a =empMapper.delEmp(7938);
        System.out.println("受影响行数：" + a);
    }

    @Test //返回实体类集合
    public void testSelectAll() {
        session = sqlSessionFactory.openSession();
        IEmpMapper empMapper=session.getMapper(IEmpMapper.class);
        List<Emp> list = empMapper.getEmpAll();
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }

    @Test
    public void testOrders(){
        session = sqlSessionFactory.openSession();
        IOrdersMapper ordersMapper=session.getMapper(IOrdersMapper.class);
        List<Orders> list = ordersMapper.getOrdersAll();
        for (Orders orders : list) {
            System.out.println(orders);
        }
    }
}
