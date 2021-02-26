package hu.bozgab.Controller;

import hu.bozgab.Entity.User;
import hu.bozgab.Service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TmpRestController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/user")
    public String user(){
        return "User";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "Admin";
    }
}
