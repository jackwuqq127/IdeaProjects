package com.zit.controller;

import com.zit.bean.Emp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/modelAttr")
public class ModelAttributeController {
    @ModelAttribute
    public void setComm(Emp emp, ModelMap modelMap){
        emp.setComm(500.0);
        System.out.println("old:"+emp);
        modelMap.put("comm",501.0);
        Emp emp1=emp.clone();
        emp1.setComm(502.0);
        //System.out.println(emp1.getComm()); //? 502
        //System.out.println(emp.getComm());  //? 500
        modelMap.addAttribute("empSave", emp1);
    }

    @RequestMapping("/testModelAttr")
    public String testModelAttr(Emp emp,@ModelAttribute("empSave")Emp saveEmp,ModelMap modelMap){
        System.out.println(modelMap.get("comm"));
        System.out.println("new:"+emp);
        System.out.println(saveEmp);
        return "success";
    }

    /**
     * @pathVarible
     * @requestParam
     * @Header
     * @CookieValue()
     * */
    @RequestMapping("/testModelAttrParam")
    public String testModelAttrParam(@ModelAttribute("comm") Double comm){
        System.out.println(comm);
        return "success";
    }
}
