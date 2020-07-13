package app.controller;

import app.entity.ArticlesEntity;
import app.restclient.response.Articles;
import app.restclient.response.News;
import app.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@SessionAttributes({ "newsList"})
@RequestMapping("/main-page")
public class MainController {

    private final NewsService newsService;

    public MainController(NewsService newsService) {
        this.newsService = newsService;
    }


    @GetMapping
    public String handle(Model model) {
        News news = newsService.getNews();
        List<Articles> articles = news.getArticles();
        articles = newsService.persistAll(articles);
        model.addAttribute("newsList", articles);
        return "main-page";
    }

    @PostMapping
    public String handlePost() {
        return "redirect:/main-page";
    }


}

