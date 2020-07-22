package app.controller;

import app.restclient.response.Articles;
import app.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@SessionAttributes({ "newsList"})
@Controller
public class SearchController {
    private final NewsService newsService;

    public SearchController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping("/search")
    public ModelAndView searchDetail(@ModelAttribute("search") String search, Model model){
        List<Articles> searchedNews = newsService.searchNews(search);
        return new ModelAndView( "search-result","newsList", searchedNews);
    }
    @GetMapping("/search-result")
    public String getSearch(){
        return  "search-result";
    }
}
