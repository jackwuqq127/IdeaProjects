package com.wuchao.servlet.emp;

import com.wuchao.bean.Emp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/emp/getEmpList")
public class EmpServlet extends EmpFrameServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session=req.getSession();
        System.out.println(session.hashCode());

        List<Emp> empList = empService.empList();
        System.out.println("JRebel 测试！");
        req.setAttribute("emplist", empList);
        req.getRequestDispatcher("/jsp/emp/empList.jsp").forward(req, resp);
    }
}
