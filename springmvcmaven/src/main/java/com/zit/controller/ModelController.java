package com.zit.controller;

import com.zit.bean.Emp;
import com.zit.views.DemoView;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping({"/model"})
public class ModelController {
    public ModelController() {
    }

    @RequestMapping({"/testModelView"})
    public ModelAndView testModelView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("modelSuccess");
        mv.addObject("msg", "ModelAndView 传参");
        return mv;
    }

    @RequestMapping({"/testModelMap"})
    public String testModelMap(ModelMap modelMap) {
        modelMap.addAttribute("msg", "ModelMap 属性传值!");
        return "modelSuccess";
    }

    @RequestMapping({"/testMap"})
    public String testMap(Map<String,Object> map) {
        map.put("msg", "Map 属性传值!");
        return "modelSuccess";
    }

    @RequestMapping("/testRedirect")
    public String testRedirect(){
        return "redirect:/model/testMap";
    }

    @RequestMapping("/testForward")
    public String testForward(){
        return "forward:/model/testMap";
    }

    @RequestMapping("/testDemoView")
    public DemoView testDemoView(){
        DemoView demoView=new DemoView();
        return demoView;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        //格式化日期类型
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));

        //comm 不进行赋值
        dataBinder.setDisallowedFields("comm");
    }

    @RequestMapping("/testConverterService")
    public String testConverterService(@RequestParam("emp") Emp emp) {
        System.out.println(emp);
        return "success";
    }

    @RequestMapping("/testEmp")
    public String testEmp(Emp emp){
        System.out.println(emp);
        return "success";
    }
}
