package hu.bozgab.Controller;


import hu.bozgab.Entity.User;
import hu.bozgab.Service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }


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

    @RequestMapping("/reg")
    public String register() {
        User user = new User("exampleUser","example@email","examplePass");
        userService.registerUser(user);
        return "Registered Succesfully";
    }

    /*@RequestMapping("/getUsers")
    public List<User> getUsers(){
        List<User> users = UserRepository.findAll();
        return users;
    }*/
}
