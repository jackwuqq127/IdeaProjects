package com.zit.controller;

import com.zit.bean.Emp;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/emp")
public class EmpController {

    /*@InitBinder
    public void initBinder(WebDataBinder dataBinder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        dataBinder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,true));
    }*/

    @RequestMapping("/testEmp")
    public String testEmp(Emp emp){
        System.out.println(emp);
        return "success";
    }

    @RequestMapping("/toAddView")
    public String toAddView(Map map){
        Emp emp = new Emp();

        map.put("emp", emp);
        map.put("command", emp);
        return "emp/add";
    }

    @RequestMapping(value = "/addEmp",method = RequestMethod.POST)
    public String addEmp(@Valid @ModelAttribute("empError") Emp emp
            ,BindingResult bindingResult, Map map){
        //System.out.println(emp);
        if(bindingResult.getErrorCount()>0){
            //System.out.println("验证失败！");
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(fieldError -> {
                String name=fieldError.getField();
                String errorMsg=fieldError.getDefaultMessage();
                //System.out.println(name+":"+errorMsg);
            });
            map.put("command", emp);
            return "emp/add";
        }
        return "success";
    }

}
