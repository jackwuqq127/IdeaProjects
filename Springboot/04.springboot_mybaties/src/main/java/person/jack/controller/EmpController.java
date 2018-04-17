package person.jack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import person.jack.dao.EmpMapper;

import java.util.Map;

@Controller
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpMapper empMapper;

    @RequestMapping("/empList")
    public String empList(Map map){
        map.put("empList", empMapper.selectAll());
        return "empList";
    }

    @RequestMapping("/testError")
    public String testError(){
        String str = "12345";
        System.out.println(str.charAt(5));
        return "ok";
    }
}
