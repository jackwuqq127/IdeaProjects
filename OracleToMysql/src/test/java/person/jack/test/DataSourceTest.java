package person.jack.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import person.jack.db.utils.OracltToMysql;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class DataSourceTest {
    @Autowired
    OracltToMysql otm;

    @Resource(name = "mysqlJdbcTemplate")
    JdbcTemplate msqlTemplate;

    @Qualifier("oracleJdbcTemplate")
    @Autowired
    JdbcTemplate template;

    @Test
    public void test() {
        otm.init(template,msqlTemplate,"emp");
        otm.start();
    }
}
