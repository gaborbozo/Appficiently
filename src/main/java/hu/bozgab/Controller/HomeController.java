package hu.bozgab.Controller;


import hu.bozgab.Entity.User;
import hu.bozgab.Service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/")
    public String defaultIndex(){ return "index.html"; }

    @RequestMapping("/index")
    public String index(){
        return "index.html";
    }

    @RequestMapping("/login")
    public String login(){ return "auth/login.html"; }

    @RequestMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user",new User());
        return "auth/registration.html";
    }

    //@RequestMapping("/register")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user, Model model) {
        userService.registerUser(user);
        model.addAttribute("email", true);
        return "redirect:/index?register";
    }
}
