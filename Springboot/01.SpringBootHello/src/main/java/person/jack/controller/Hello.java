package person.jack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import person.jack.bean.Person;

import java.util.Map;

@RestController
public class Hello {

    @Autowired
    private ApplicationContext ctx;

    @ResponseBody
    @GetMapping("/readConfig")
    public String readConfig(String attr){
        return ctx.getEnvironment().getProperty(attr);
    }

    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        String loc=ctx.getEnvironment().getProperty("loc");
        System.out.println(loc);
        return "Hello springboot!";
    }

    @ResponseBody
    @RequestMapping("/getPerson/{id}")
    public Person getPerson(@PathVariable Integer id){
        Person person=new Person();
        person.setId(id);
        person.setName("wuchao");
        return person;
    }
}
