package hu.bozgab.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String index(){
        return "Main";
    }

    @RequestMapping("/user")
    public String user(){
        return "User";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "Admin";
    }

    /*@RequestMapping("/getUsers")
    public List<User> getUsers(){
        List<User> users = authorizationService.findAll();
        return users;
    }*/
}
