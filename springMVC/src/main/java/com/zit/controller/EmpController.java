package com.zit.controller;

import com.zit.bean.Dept;
import com.zit.bean.Emp;
import com.zit.dao.DeptMapper;
import com.zit.dao.EmpMapper;
import com.zit.exception.GlobleException;
import com.zit.exception.NoUserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @RequestMapping("/testEmp")
    public String testEmp(){
        System.out.println("testEmp");
        return "success";
    }

    @RequestMapping("/empList")
    public String empList(ModelMap modelMap) {
        //System.out.println("获取所有员工数据！");
        List<Emp> empList = empMapper.empList();
        modelMap.put("emplist", empList);
        return "emp/list";
    }

    @RequestMapping("/deleteEmp/{empno}")
    public String deleteEmp(@PathVariable("empno") int empno) {
        empMapper.deleteByPrimaryKey(empno);
        return "redirect:/emp/empList";
    }

    @RequestMapping("/addView")
    public String addView(ModelMap modelMap) {
        List<Dept> deptList = deptMapper.deptList();
        modelMap.addAttribute(deptList);
        Emp emp=new Emp();
        modelMap.put("emp",emp);
        modelMap.put("command",emp );
        return "emp/add";
    }

    @RequestMapping("/addEmp")
    public String addEmp(@Valid Emp emp, BindingResult result,ModelMap modelMap) {
        System.out.println(emp);
        if(result.getErrorCount()>0){
            System.out.println("数据验证失败！");
            List<FieldError> errors = result.getFieldErrors();
            errors.forEach(fieldError -> {
                System.out.println(fieldError.getField()+":"+fieldError.getDefaultMessage());
            });
            List<Dept> deptList = deptMapper.deptList();
            modelMap.addAttribute(deptList);
            modelMap.put("command", emp);
            return "emp/add";
        }
        empMapper.insert(emp);
        return "redirect:/emp/empList";
    }

    @RequestMapping("/testGetEmp")
    public String testGetEmp(Emp emp){
        System.out.println(emp);
        return "success";
    }

    @ResponseBody
    @RequestMapping("/testJson")
    public List<Map> testJson(){
        List<Map> list = new ArrayList<>();
        Map map = new HashMap();
        map.put("empno", 7369);
        map.put("name","ALLEN");
        map.put("job","SALESMAN");
        list.add(map);

        map = new HashMap();
        map.put("empno", 7499);
        map.put("name","SMITH");
        map.put("job","CLERK");
        list.add(map);

        map = new HashMap();
        map.put("empno", 7521);
        map.put("name","WARD");
        map.put("job","SALESMAN");
        list.add(map);

        return list;
    }

    //@ResponseBody
    @RequestMapping("/testJsonObj")
    public Map testJsonObj(){
        Map map = new HashMap();
        map.put("empno", 7369);
        map.put("name","ALLEN");
        map.put("job","SALESMAN");
        map.put("mgr", "7698");
        map.put("sal", 800);
        return map;
    }

    @ResponseBody
    @RequestMapping("/testDownload")
    public byte[] testDownload(HttpServletRequest req, HttpServletResponse resp){
        resp.setContentType("text/plain");
        String path=req.getServletContext().getRealPath("/js/jquery-3.2.1.min.js");
        InputStream in = req.getServletContext().getResourceAsStream("/js/jquery-3.2.1.min.js");
        byte[] retByte=null;
        try {
            retByte = new byte[in.available()];
            in.read(retByte);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retByte;
    }

    @ResponseBody
    @RequestMapping(value = "/testRequestBody",method = RequestMethod.POST)
    public String testRequestBody(@RequestBody String body,HttpServletResponse resp,String fileName) throws IOException {
        System.out.println(body);
        System.out.println(fileName);
        resp.setContentType("text/html;charset=utf-8");
        return "这里是重庆市渝北区大数据学院！";
    }

    @RequestMapping("/testHttpEntity")
    public String testHttpEntity(HttpEntity<String> httpEntity){
        HttpHeaders headers = httpEntity.getHeaders();
        headers.keySet().forEach(key->{
            String value = headers.get(key).toString();
            System.out.println(key+":"+value);
        });
        String body = httpEntity.getBody();
        System.out.println("---------------");
        System.out.println(body);
        return "success";
    }

    @RequestMapping("/testHttpEntityDownLoad")
    public HttpEntity<byte[]> testHttpEntityDownLoad(HttpServletRequest req) throws IOException {
        String fileName="jquery-3.2.1.min.js";
        InputStream in = req.getServletContext().getResourceAsStream("/js/"+fileName);
        byte[] retByte=new byte[in.available()];
        in.read(retByte);

        HttpHeaders headers = new HttpHeaders();
        String filename = new String(fileName.getBytes("utf-8"),"iso8859-1");
        headers.add("Content-Disposition", "attachment;filename="+filename);

        ResponseEntity responseEntity = new ResponseEntity(retByte,headers,HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping("/setLanguage")
    public String setLanguage(Locale locale){
        String val = messageSource.getMessage("home", null, locale);
        //System.out.println(val);
        return "redirect:/index";
    }

    @RequestMapping("/upload")
    public String upload(@RequestParam("desc") String[] descs
            , @RequestParam("files")MultipartFile[] files, HttpServletRequest req, Map map) throws IOException {
        if(files.length==0){
            map.put("mesg", "文件不存在");
            return "Error";
        }
        String realPath = req.getServletContext().getRealPath("/fileupload");
        String desc=null;
        for (int i = 0; i< files.length; i++) {
            MultipartFile f = files[i];
            desc = descs[i];
            File toSave = new File(realPath,desc+"-"+f.getOriginalFilename());
            if(!toSave.exists()){
                toSave.mkdirs();
            }
            f.transferTo(toSave);
        }
        return "success";
    }

    /*@ExceptionHandler({RuntimeException.class })
    public ModelAndView handlerException2(Exception e){
        ModelAndView modelAndView = new ModelAndView("Error");
        modelAndView.addObject("mesg", "RuntimeException: Controller异常处理:"+e.getMessage());
        return modelAndView;
    }*/

    /*@ExceptionHandler({ArithmeticException.class,IllegalStateException.class })
    public ModelAndView handlerException(Exception e){
        ModelAndView modelAndView = new ModelAndView("Error");
        modelAndView.addObject("mesg", "Controller异常处理:"+e.getMessage());
        return modelAndView;
    }*/

    @RequestMapping("/testException")
    public String testException(int num){
        System.out.println(100/num);
        return "success";
    }

    @RequestMapping("/testNoUserName")
    public void testNoUserName(String userName,HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.sendError(500,"没有权限！");
        return;
    }

    @RequestMapping(value = "/simpleMappingException",produces="text/html;charset=utf-8")
    public String simpleMappingException(int i){
       // ResponseStatusExceptionResolver
        String str="这里是重庆大数据学院！";
        char c=str.charAt(i);
        System.out.println(c);

        return "success";
    }
}
