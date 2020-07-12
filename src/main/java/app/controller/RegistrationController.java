package app.controller;

import app.dto.UserDTO;
import app.exception.UserAlreadyExistException;
import app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("registration")
    public String handle_get(Model model) {
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

    @PostMapping(value = "/registration")
    public ModelAndView handle_post(@Valid @ModelAttribute(value = "user") UserDTO user, BindingResult result) {
        if (result.hasErrors())
            return new ModelAndView("registration", "user", user);
        if (userService.isRegistered(user.getEmail()))
            throw new UserAlreadyExistException("There is an account with that email address:" + user.getEmail());
        userService.persist(user);
        return new ModelAndView("successRegister", "successRegister", user);
    }

    @GetMapping("successRegister")
    public String getSuccessRegisterPage() {
        return "successRegister";
    }


}
