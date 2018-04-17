package person.jack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AppController {
    @GetMapping("/info")
    public @ResponseBody String info(HttpServletRequest req){
        return "springBoot 是javaWEB开发最优美的姿势！"+req.getRequestURL();
    }
}
