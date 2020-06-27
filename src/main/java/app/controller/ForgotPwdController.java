package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForgotPwdController {

    @RequestMapping("/forgot-password")
    public String handle(){
        return "forgot-password";
    }

}
