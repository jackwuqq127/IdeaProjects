//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zit.controller;

import com.zit.bean.Emp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping({"/hello"})
public class HelloController {
    public HelloController() {
    }

    @RequestMapping({"/"})
    public String index() {
        return "index";
    }

    @RequestMapping({"/sayHello"})
    public String hello() {
        System.out.println("this is the hello page.");
        return "hello";
    }

    @RequestMapping(
            value = {"/testMethod"},
            method = {RequestMethod.POST}
    )
    public String testMethod() {
        return "hello";
    }

    @RequestMapping(
            value = {"/testMethodParams"},
            method = {RequestMethod.GET},
            params = {"id", "name=zit", "passd!=123456"}
    )
    public String testMethodParam() {
        return "hello";
    }

    @RequestMapping(
            value = {"/testMethodParamHead"},
            method = {RequestMethod.GET},
            params = {"id"},
            headers = {"Host=localhost:8080"}
    )
    public String testMethodParamHead() {
        return "hello";
    }

    @RequestMapping({"/testant/*/?/**/sayHello??"})
    public String testAnt() {
        return "hello";
    }

    @RequestMapping({"/testRestGet/{id}"})
    public String testRestGet(@PathVariable("id") String id) {
        System.out.println("id=> " + id);
        return "hello";
    }

    @ResponseBody
    @RequestMapping(
            value = {"/testput"},
            method = {RequestMethod.PUT}
    )
    public String testPUT() {
        System.out.println("你好，这里是PUT请求，用于【更新】资源！");
        return "success";
    }

    @RequestMapping(
            value = {"/testdelete"},
            method = {RequestMethod.DELETE}
    )
    @ResponseBody
    public String testDELETE() {
        System.out.println("你好，这里是DELETE请求，用于【删除】资源！");
        return "success";
    }

    @RequestMapping({"/testParameter"})
    public String testParameter(@RequestParam(value = "id", required = false, defaultValue = "000") String id) {
        System.out.println(id);
        return "success";
    }

    @RequestMapping({"/testheader"})
    public String testHeader(@RequestHeader("User-Agent") String userAgent, @RequestHeader("Cookie") String cookie) {
        System.out.println("userAgent: " + userAgent);
        System.out.println("cookie: " + cookie);
        return "success";
    }

    @RequestMapping({"/testcookie"})
    public String testCookie(@CookieValue("JSESSIONID") String sessionId) {
        System.out.println(sessionId);
        return "success";
    }

    @RequestMapping({"/testemp"})
    public String testUserPojo(Emp emp) {
        System.out.println(emp.getEname());
        System.out.println(emp.getDept().getDname());
        return "success";
    }

    @RequestMapping({"/testServletApi"})
    public void testServletApi(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ename = req.getParameter("ename");
        String dname = req.getParameter("dept.dname");
        System.out.println(ename);
        System.out.println(dname);
        req.setAttribute("ename", ename);
        req.setAttribute("dname", dname);
        req.getRequestDispatcher("/WEB-INF/views/success.jsp").forward(req, resp);
    }
}
