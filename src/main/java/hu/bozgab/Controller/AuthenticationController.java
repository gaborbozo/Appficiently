package hu.bozgab.Controller;


import hu.bozgab.Entity.User;
import hu.bozgab.Service.Interface.IUserService;
import hu.bozgab.Service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AuthenticationController {

    IUserService userService;

    @Autowired
    public void setUserService(IUserService userService){ this.userService = userService; }

    @RequestMapping("/")
    public String defaultIndex(HttpSession session){

        return "index.html";

    }

    @RequestMapping("/index")
    public String index(){

        return "index.html";

    }

    @RequestMapping("/login")
    public String login(HttpSession session){

        return "auth/login.html";

    }

    @RequestMapping("/registration")
    public String registration(Model model) {

        model.addAttribute("user",new User());

        return "auth/registration.html";
    }

    @PostMapping("/validateRegistration")
    //@RequestMapping(value = "/register", method = RequestMethod.POST)
    public String validateregistration(@ModelAttribute User user) {

        userService.registerUser(user);

        return "redirect:/index?registered";
    }

    @RequestMapping("/settings")
    public String getuser(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model){

        model.addAttribute("user", userDetails);

        return "auth/settings.html";
    }
}
