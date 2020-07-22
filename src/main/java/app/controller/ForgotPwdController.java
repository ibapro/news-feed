package app.controller;

import app.dto.UserDTO;
import app.service.EmailServiceImpl;
import app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.UUID;

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
    public String handlePost(final HttpServletRequest request, @ModelAttribute(value = "email") String email) {
        UserDTO user = userService.findByEmail(email);
        if (Objects.isNull(user)) {
            return "redirect:/forgot-password?emailError";
        }
        final String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);
        emailService.sendResetTokenEmail(getAppUrl(request), token, user);
        return "index";
    }
    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}

