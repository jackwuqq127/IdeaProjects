package com.wuchao.servlet.emp;

import com.wuchao.listener.LoadApplicationContext;
import com.wuchao.service.EmpService;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class EmpFrameServlet extends HttpServlet {
    protected ApplicationContext ctx;
    protected EmpService empService;

    @Override
    public void init() throws ServletException {
        ctx= LoadApplicationContext.ctx;
        empService = ctx.getBean(EmpService.class);
    }
}
