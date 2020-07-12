package app.controller;

import app.restclient.response.Articles;
import app.restclient.response.News;
import app.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes({ "newsList"})
public class MainController {

    private final NewsService newsService;

    public MainController(NewsService newsService) {
        this.newsService = newsService;
    }


    @GetMapping("/main-page")
    public String handle(Model model) {
        News news = newsService.getNews();
        List<Articles> articles = news.getArticles();
        articles = newsService.persistAll(articles);
        model.addAttribute("newsList", articles);
        return "main-page";
    }

    @PostMapping("/main-page")
    public String handlePost(Model model) {
        News news = newsService.getNews();
        List<Articles> articles = news.getArticles();
        articles = newsService.persistAll(articles);
        model.addAttribute("newsList", articles);
        return "main-page";
    }

}

