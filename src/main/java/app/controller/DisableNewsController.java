package app.controller;

import app.restclient.response.Articles;
import app.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@Controller
public class DisableNewsController {

    private final NewsService newsService;

    public DisableNewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/disable-news")
    public String handle(Model model) {
        List<Articles> news = newsService.getUserArticles();
        model.addAttribute("userNews", news);
        return "disable-news";
    }
    @GetMapping("/disable-news/delete")
    public String handle(@RequestParam int id) {
        List<Articles> news = newsService.deleteUserArticlesById(id);
        return "redirect:/disable-news";
    }


}
