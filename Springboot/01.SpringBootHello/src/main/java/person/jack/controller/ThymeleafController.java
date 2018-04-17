package person.jack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class ThymeleafController {
    @RequestMapping("/toHello")
    public String toHello(Map map) {
        map.put("hello","Hello,thymeleaf!");
        return "/hello";
    }
}
