package test;

import com.wuchao.bean.Dept;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class TestDept {
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
    public void getDeptFull(){
        session=sqlSessionFactory.openSession();
        List<Dept> list = session.selectList("com.wuchao.bean.Dept.getDeptFull");
        for (Dept dept : list) {
            System.out.println(dept);
        }
    }

    @Test
    public void getDeptFull2(){
        session=sqlSessionFactory.openSession();
        List<Dept> list = session.selectList("com.wuchao.bean.Dept.getDeptFull2");
        for (Dept dept : list) {
            System.out.println(dept);
        }
    }
}
