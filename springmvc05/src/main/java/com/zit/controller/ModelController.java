package com.zit.controller;

import com.zit.bean.Emp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/model")
public class ModelController {
    @RequestMapping("/testParameter")
    public String testParameter(String id,@RequestParam("ename")String name){
        System.out.println(id);
        System.out.println(name);
        return "modelSuccess";
    }

    @RequestMapping("/testHeader")
    public String testHeader(@RequestHeader("User-Agent") String userAgent
            ,@RequestHeader("Cookie")String cookie){
        System.out.println(userAgent);
        System.out.println(cookie);
        return "modelSuccess";
    }

    @RequestMapping("/testCookie")
    public String testCookie(@CookieValue("JSESSIONID")String sessionId){
        System.out.println(sessionId);
        return "modelSuccess";
    }

    //?empno=7369&ename=FORD&mgr.ename=King&dept.deptno=10&dept.dname=SALER
    @RequestMapping("/testpojo")
    public String testPojo(Emp emp){
        System.out.println(emp);
        return "modelSuccess";
    }

    //?empno=7369&ename=FORD&mgr.ename=King&dept.deptno=10&dept.dname=SALER
    @RequestMapping("/testServletApi")
    public void testServletApi(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String empno=req.getParameter("empno");
        String ename = req.getParameter("ename");
        String deptDname=req.getParameter("dept.dname");
        System.out.println(empno);
        System.out.println(ename);
        System.out.println(deptDname);
        req.getRequestDispatcher("/WEB-INF/views/modelSuccess.jsp").forward(req,resp);
    }
    @RequestMapping("/testPojoServletAPI")
    public String testPojoServletAPI(Emp emp,HttpServletRequest req){
        req.setAttribute("msg","参数组装对象:"+emp);
        return "modelSuccess";
    }
    @RequestMapping("/testCookieSessionId")
    public String testCookieSessionId(@CookieValue("JSESSIONID")String jsessionId
            ,HttpSession session){
        System.out.println("jsessionId:"+jsessionId);
        String hsessionId=session.getId();
        System.out.println("hsessionId:"+hsessionId);
        return "modelSuccess";
    }

}
