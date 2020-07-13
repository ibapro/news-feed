package app.controller;

import app.restclient.response.Articles;
import app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OpenTabController {
    private final UserService userService;

    public OpenTabController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/open-tab")
    public ModelAndView handle(@RequestParam(name = "id") String id, HttpSession model) {
        List<Articles> articles = (List<Articles>) model.getAttribute("newsList");
        Articles news = articles.get(Integer.parseInt(id));
        userService.addNews(news);
        return new ModelAndView("open-tab", "news", news);
    }
}
