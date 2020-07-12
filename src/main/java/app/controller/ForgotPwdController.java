package app.controller;

import app.service.EmailServiceImpl;
import app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ForgotPwdController {

    private final EmailServiceImpl emailService;
    private final UserService userService;

    public ForgotPwdController(EmailServiceImpl emailService, UserService userService) {
        this.emailService = emailService;
        this.userService = userService;
    }

    @GetMapping("/forgot-password")
    public String handle() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String handlePost(@ModelAttribute(value = "email") String email) {
        if (!userService.isRegistered(email)) {
           return "redirect:/forgot-password?emailError";
        }
        emailService.sendEmail(email, "Reset Password", userService.changePassword(email));
        return "index";
    }

}

