package capstone.markproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
    This controller is responsible for rendering a login page (login.html) when users navigate to /login endpoint.
 */

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String login() {
        logger.warn("login page: Success!");
        return "login";
    }


}
