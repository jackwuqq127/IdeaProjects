package test;

import com.wuchao.bean.Emp;
import com.wuchao.bean.Orders;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TestMybatiesAtXml {
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

    @Test //insert 操作
    public void testInsert() {
        //从sqlsession 工厂中打开session,默认为手动提交
        session = sqlSessionFactory.openSession();
        //准备对象（参数）
        Emp emp = new Emp();
        emp.setEmpno(1234);
        emp.setEname("悟空");
        emp.setSal(1000);
        emp.setJob("保镖");
        //执行insert 操作
        int a = session.insert("com.wuchao.bean.Emp.addEmp", emp);
        //提交事务
        session.commit();
        System.out.println("受影响行数：" + a);
    }

    @Test //update 操作
    public void testUpdate() {
        //打开一个自动提交的session
        session = sqlSessionFactory.openSession(true);
        Emp emp = new Emp();
        emp.setEmpno(1234);
        emp.setEname("悟空");
        emp.setSal(10000);
        emp.setJob("大保镖");
        emp.setHiredate(new Date());

        int a = session.update("com.wuchao.bean.Emp.updEmp", emp);
        System.out.println("受影响行数：" + a);
    }

    @Test //delete 操作
    public void testDelete() {
        session = sqlSessionFactory.openSession(true);
        int a = session.delete("com.wuchao.bean.Emp.delEmp", 1234);
        System.out.println("受影响行数：" + a);
    }

    @Test //查询返回单个实体类
    public void testSelect() {
        session = sqlSessionFactory.openSession();
        Emp emp = session.selectOne("com.wuchao.bean.Emp.getEmpByEmpno", 7521);
        System.out.println(emp);
    }

    @Test //返回实体类集合
    public void testSelectAll() {
        session = sqlSessionFactory.openSession();
        List<Emp> list = session.selectList("com.wuchao.bean.Emp.getEmpAll");
        for (Emp e : list) {
            System.out.println(e);
        }
    }

    @Test //返回map list
    public void testSelectAllMap(){
        session = sqlSessionFactory.openSession();
        List<Map> list = session.selectList("com.wuchao.bean.Emp.getEmpAllMap");
        for (Map map : list) {
            System.out.println(map);
        }
    }

    @Test
    public void testOrders(){
        session = sqlSessionFactory.openSession();
        List<Orders> list = session.selectList("com.wuchao.bean.Orders.getOrdersAll");
        for (Orders o : list) {
            System.out.println(o);
        }
    }

    @Test
    public void testGetEmpDept(){
        session = sqlSessionFactory.openSession();
        List<Emp> list=session.selectList("com.wuchao.bean.Emp.getEmpDept");
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }

    @Test
    public void testGetEmpDeptMap(){
        session = sqlSessionFactory.openSession();
        List<Emp> list=session.selectList("com.wuchao.bean.Emp.getEmpDeptMap");
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }

    @Test
    public void getEmpDeptMap3(){
        session = sqlSessionFactory.openSession();
        List<Emp> list=session.selectList("com.wuchao.bean.Emp.getEmpDeptMap3");
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }
}
