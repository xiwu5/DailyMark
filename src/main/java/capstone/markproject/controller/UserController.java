package capstone.markproject.controller;

import capstone.markproject.entity.User;
import capstone.markproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
   UserController manages the user interface aspects of user registration,
   interacts with the UserService for business logic
 */

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        logger.warn("celine showRegistrationForm");
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        logger.warn("celine registerUser: " + user.getPassword());
        userService.registerUser(user);
        return "redirect:/login";
    }
}
