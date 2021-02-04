package hu.bozgab.Controller;


import hu.bozgab.Entities.User;
import hu.bozgab.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorizationController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/getUsers")
    public List<User> getUsers(){
        List<User> users = userRepository.findAll();
        return users;
    }

    @RequestMapping("/")
    public String index(){
        return "Főoldal";
    }

    //@Secured("ROLL_USER")
    //@PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/login")
    public String login(){
        return "Login";
    }
    //@Secured("ROLL_ADMIN")
    //@PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/admin")
    public String admin(){
        return "Admin";
    }
}
