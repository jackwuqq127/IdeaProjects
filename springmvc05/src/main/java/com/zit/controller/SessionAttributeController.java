package com.zit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;


@Controller @SessionAttributes(value = {"msg"},types = {Double.class})
@RequestMapping("/session")
public class SessionAttributeController {
    @RequestMapping("/testByName")
    public String testByName(ModelMap modelMap){
        //放入请求域
        modelMap.put("msg","session自动赋值！");
        return "sessionAttrSuccess";
    }

    @RequestMapping("/testByType")
    public String testByType(Map map){
        map.put("sal", 5.5);
        map.put("com", 4.3);
        return "sessionAttrSuccess";
    }
}

