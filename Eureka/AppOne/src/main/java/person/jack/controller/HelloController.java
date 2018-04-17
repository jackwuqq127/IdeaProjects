package person.jack.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class HelloController {

    @Value("${spring.application.name}")
    private String springAppName;

    @Value("${spring.profiles.active}")
    private String active;

    @RequestMapping(value = "/sayMesg/{word}")
    public @ResponseBody String sayMesg(@PathVariable String word){
        if(word==null){
            return "未接收到参数！";
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < word.length(); i++) {
            int acii = word.charAt(i);
            buffer.append(acii + ",");
        }
        return active+"====>"+ buffer.toString() ;
    }

    @RequestMapping(value = "/infos")
    public @ResponseBody String info(){
        System.out.println("info");
        return "这里是AppOne，端口："+active;
    }

    public static boolean isHealth=true;
    @GetMapping("/setHealth/{health}")
    public @ResponseBody boolean setHealth(@PathVariable boolean health){
        isHealth=health;
        return health;
    }
}
