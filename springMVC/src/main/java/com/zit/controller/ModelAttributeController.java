//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zit.controller;

import com.zit.bean.Emp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/modelAttr"})
public class ModelAttributeController {
    public ModelAttributeController() {
    }

    @ModelAttribute
    public void setComm(Emp emp, ModelMap modelMap) {
        System.out.println("old:" + emp);
        emp.setComm(500.0);
        modelMap.put("comm", 505);
    }

    @RequestMapping({"/testModelAttr"})
    public String testModelAttr(Emp emp) {
        return "modelSuccess";
    }

    @RequestMapping({"/testModelAttrParam"})
    public String testModelAttrParam(Emp saveEmp
            , @ModelAttribute("comm") int comm) {
        System.out.println("new:" + saveEmp);
        System.out.println("comm:" + comm);
        return "modelSuccess";
    }
}
