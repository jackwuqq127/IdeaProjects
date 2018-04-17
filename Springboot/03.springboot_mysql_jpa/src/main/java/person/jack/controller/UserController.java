package person.jack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import person.jack.bean.User;
import person.jack.dao.UserRepository;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/addUser")
    public String addUser(Map map,User user){
        userRepository.save(user);
        map.put("mesg", user);
        return "result";
    }
}
