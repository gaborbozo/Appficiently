package hu.bozgab.Controller;


import hu.bozgab.Entities.User;
import hu.bozgab.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationService authorizationService;

    @RequestMapping("/")
    public String index(){
        return "Főoldal";
    }

    @RequestMapping("/user")
    public String user(){
        return "User";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "Admin";
    }

    @RequestMapping("/getUsers")
    public List<User> getUsers(){
        List<User> users = authorizationService.findAll();
        return users;
    }
}
