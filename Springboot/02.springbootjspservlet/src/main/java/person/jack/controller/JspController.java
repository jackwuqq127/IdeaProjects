package person.jack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class JspController {

    @RequestMapping("/index")
    public String index(Map map){
        map.put("mesg", "jsp mesage");
        return "index";
    }
}
