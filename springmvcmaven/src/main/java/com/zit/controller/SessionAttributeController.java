package com.zit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;


/**
 * @author 吴超
 */
@SessionAttributes(
        value = {"msg"},
        types = {Integer.class}
)
@Controller
@RequestMapping({"/session"})
public class SessionAttributeController {
    public SessionAttributeController() {

    }

    @RequestMapping({"/testbyName"})
    public String testByName(ModelMap modelMap) {
        modelMap.addAttribute("msg", "sessionAttribute测试！");
        return "sessionAttributeSuccess";
    }

    @RequestMapping({"/testByType"})
    public String testByType(Map map) {
        map.put("sal", Integer.valueOf(500));
        map.put("comm", Integer.valueOf(100));
        return "sessionAttributeSuccess";
    }
}
