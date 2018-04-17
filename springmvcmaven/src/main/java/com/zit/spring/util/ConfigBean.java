package com.zit.spring.util;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class ConfigBean {

    @Bean("druidDataSource")
    public DruidDataSource setDruidDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        InputStream in = ConfigBean.class.getClassLoader().getResourceAsStream("databases.properties");
        try {
            Properties properties = new Properties();
            properties.load(in);
            druidDataSource.setConnectProperties(properties);
            druidDataSource.init();
            return druidDataSource;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
