package app.controller;

import app.dto.PasswordDTO;
import app.entity.User;
import app.exception.TokenExpiredException;
import app.service.UserSecurityService;
import app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@SessionAttributes({ "tokenValue"})
public class ChangePasswordController {
    private final UserSecurityService userSecurityService;
    private final UserService userService;

    public ChangePasswordController(UserSecurityService userSecurityService, UserService userService) {
        this.userSecurityService = userSecurityService;
        this.userService = userService;
    }

    @GetMapping("/change-password")
    public String handle(final Model model, @RequestParam("token") String token) {
        String resetToken = userSecurityService.validatePasswordResetToken(token);
        if (resetToken != null) {
            throw new TokenExpiredException("You have lost your time");
        } else {
            model.addAttribute("tokenValue", token);
            model.addAttribute("passwordDto", new PasswordDTO());
        }
        return "change-password";
    }

    @PostMapping("/change-password")
    public ModelAndView handlePost(@Valid @ModelAttribute(value = "passwordDto") PasswordDTO passwordDto, BindingResult bindingResult, HttpSession model) {
        if (bindingResult.hasErrors())
            return new ModelAndView("redirect:/change-password?token="+
                    model.getAttribute("tokenValue"),"passwordDto",passwordDto);

        passwordDto.setToken((String) model.getAttribute("tokenValue"));
        final String result = userSecurityService.validatePasswordResetToken(passwordDto.getToken());

        if (result != null) {
            throw new TokenExpiredException("You have lost your time"); }

        Optional<User> user = userService.getUserByPasswordResetToken(passwordDto.getToken());
        user.ifPresent(value -> userService.changePasswordWithToken(value, passwordDto.getPassword()));

        return new ModelAndView("redirect:/index");

    }
}
