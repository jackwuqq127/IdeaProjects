package person.wuchao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Example {

    @RequestMapping("/")
    String home() {
        System.out.println("++");
        System.out.println("---");
        return "Hello World!";
    }

}
