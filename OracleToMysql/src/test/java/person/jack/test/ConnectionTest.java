package person.jack.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class ConnectionTest {
    @Qualifier("oracleJdbcTemplate")
    @Autowired
    JdbcTemplate jdbcTemplate;

    int num = 800;
    CyclicBarrier cb = new CyclicBarrier(num);
    CountDownLatch cd = new CountDownLatch(num);

    @Test
    public void testConnection() throws SQLException {
        QueryRunner qr=new QueryRunner();
        Connection connection = jdbcTemplate.getDataSource().getConnection();
        System.out.println(connection);
    }

    @Test
    public void testMultiThread() {
        String sql = "select * from people where name='" + UUID.randomUUID() + "'";
        for (int i = 0; i < num; i++) {
            Runnable task = new SqlRun(sql);
            new Thread(task).start();
        }
        try {
            cd.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class SqlRun implements Runnable {
        String sql;
        Lock lock = new ReentrantLock();

        @Override
        public void run() {
            try {
                cb.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String name = Thread.currentThread().getName();
                long l1 = System.currentTimeMillis();
                DruidDataSource dataSource = (DruidDataSource)jdbcTemplate.getDataSource();
                System.out.println(dataSource.getConnection());
                List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

                long l2 = System.currentTimeMillis();
                System.out.println(name + "===>" + list.size() + "耗时：" + (l2 - l1) / 1000.0 + "秒>>"+dataSource.getActiveCount());
                cd.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //lock.unlock();
            }
        }

        public SqlRun(String sql) {
            this.sql = sql;
        }
    }
}
