package com.zit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/modelview")
public class ModelAndViewController {

    @RequestMapping("/testModelView")
    public ModelAndView testModelView(){
        Map map = new HashMap();
        map.put("name", "宋襄公");
        map.put("age", 60);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("modelAndView");
        modelAndView.addObject("msg","服务端传参");
        modelAndView.addAllObjects(map);
        return modelAndView;
    }

    @RequestMapping("/testModelMap")
    public String testModelMap(ModelMap modelMap){
        modelMap.addAttribute("name", "齐桓公");
        modelMap.put("age", 70);
        return "modelAndView";
    }

    @RequestMapping("/testModel")
    public String testModel(Model model){
        System.out.println(model.getClass().getName());
        model.addAttribute("name", "齐桓公");
        model.addAttribute("age", 71);
        return "modelAndView";
    }

    @RequestMapping("/testMap")
    public String testMap(Map map){
        System.out.println(map.getClass().getName());
        map.put("name", "齐桓公");
        map.put("age", 72);
        return "modelAndView";
    }

}
