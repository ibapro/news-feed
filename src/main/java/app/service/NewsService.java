package app.service;

import app.configuration.ConfigUrl;
import app.entity.ArticlesEntity;
import app.entity.User;
import app.helper.CommonHelper;
import app.mapper.ArticlesMapper;
import app.repository.NewsRepo;
import app.repository.UserRepo;
import app.restclient.response.Articles;
import app.restclient.response.News;
import org.slf4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class NewsService {

    private final RestTemplate rest;
    private final Logger logger;
    private final NewsRepo newsRepo;
    private final UserRepo userRepo;


    public NewsService(RestTemplate rest, Logger logger, NewsRepo newsRepo, UserRepo userRepo) {
        this.rest = rest;
        this.logger = logger;
        this.newsRepo = newsRepo;
        this.userRepo = userRepo;
    }

    public News getNews() {
        String url = ConfigUrl.getUrl();
        try {
            return rest.getForObject(url, News.class);
        } catch (HttpStatusCodeException e) {
            logger.error(e.getLocalizedMessage(), e);
            return new News();
        }
    }

    public Articles persist(Articles articles) {
        String timeForRead = CommonHelper.countTimeForRead(articles.getContent());
        articles.setTimeForRead(timeForRead);
        ArticlesEntity articlesEntity = ArticlesMapper.INSTANCE.articlesToEntity(articles);
        try {
            articlesEntity = newsRepo.save(articlesEntity);
            return ArticlesMapper.INSTANCE.entityToArticles(articlesEntity);
        } catch (DataIntegrityViolationException ex) {
            articlesEntity = newsRepo.findByUrl(articlesEntity.getUrl())
                    .get();
            return ArticlesMapper.INSTANCE
                    .entityToArticles(articlesEntity);
        }
    }

    public List<Articles> persistAll(List<Articles> articles) {
        return articles.stream().map(articlesN -> persist(articlesN)).collect(Collectors.toList());
    }

    public List<Articles> getUserArticles() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userRepo.findByEmail(user.getEmail());
        List<ArticlesEntity> entities = user.getArticles();
        return ArticlesMapper.INSTANCE
                .entityListToArticlesList(entities);
    }

    public List<Articles> deleteUserArticlesById(int id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userRepo.findByEmail(user.getEmail());
        user.getArticles().remove(id);
        user = userRepo.save(user);
        List<ArticlesEntity> entities = user.getArticles();
        return ArticlesMapper.INSTANCE
                .entityListToArticlesList(entities);
    }

    public List<Articles> searchNews(String title) {
        List<ArticlesEntity> entities = newsRepo
                .findAll().stream()
                .filter(x -> x.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
        return ArticlesMapper.INSTANCE
                .entityListToArticlesList(entities);
    }

}
