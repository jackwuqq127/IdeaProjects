package com.wuchao.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class LoadApplicationContext implements ServletContextListener {
    public static ApplicationContext ctx;

    @Override
    public void contextInitialized(ServletContextEvent sc) {
        ctx = WebApplicationContextUtils.getWebApplicationContext(sc.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}
