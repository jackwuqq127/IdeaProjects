package com.zit.spring;

import com.alibaba.druid.pool.DruidDataSource;
import com.zit.bean.Emp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class Configure {
    @Bean
    public Emp setEmp(){
        Emp emp=new Emp();
        emp.setEname("scott");
        return emp;
    }

    @Bean(name = "druidDataSource")
    public DruidDataSource setDruidDataSource() throws IOException, SQLException {
        InputStream in=this.getClass().getClassLoader().getResourceAsStream("databases.properties");
        Properties info = new Properties();
        info.load(in);
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.configFromPropety(info);
        druidDataSource.init();
        return druidDataSource;
    }
}
