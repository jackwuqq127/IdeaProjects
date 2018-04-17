package person.jack.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Repository
public class DaoUtil {
    @Autowired
    private DataSource dataSource;

}
