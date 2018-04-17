package com.zit.controller;

import com.zit.bean.Emp;
import com.zit.view.JsonView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/view")
public class ViewController {
    @RequestMapping("/testView")
    public String testView(){
        System.out.println("testView");
        return "success";
    }

    @RequestMapping("/testRedirect")
    public String testRedirect(){
        System.out.println("重定向测试！");
        return "redirect:/view/testView";
    }

    @RequestMapping("/testForward")
    public String testForward(){
        System.out.println("内部转发测试！");
        return "forward:/view/testView";
    }

    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping("/testJson")
    public JsonView testJson(Map map){
        JsonView jsonView=new JsonView();
        List<Emp> list = new ArrayList<>();

        Emp emp=new Emp(7369,"scott",50.0);
        list.add(emp);

        emp=new Emp(7370,"word",52.0);
        list.add(emp);

        emp=new Emp(7371,"Jack",152.0);
        list.add(emp);

        map.put("empList", list);
        map.put("msg", "员工列表");
        return jsonView;
    }

    @RequestMapping("/testConversion")
    public String testConversion(@RequestParam("emp")Emp emp){
        System.out.println(emp);
        return "success";
    }
}
