package app.service;

import app.configuration.ConfigUrl;
import app.entity.ArticlesEntity;
import app.entity.User;
import app.mapper.ArticlesMapper;
import app.restclient.response.Articles;
import app.restclient.response.News;
import app.repo.NewsRepo;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class NewsService {

    private final RestTemplate rest;
    private final Logger logger;
    private final NewsRepo newsRepo;

    public NewsService(RestTemplate rest, Logger logger, NewsRepo newsRepo) {
        this.rest = rest;
        this.logger = logger;
        this.newsRepo = newsRepo;
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
        List<ArticlesEntity> entities = user.getArticles();
        return ArticlesMapper.INSTANCE
                .entityListToArticlesList(entities);
    }
    public List<Articles> deleteUserArticlesById(int id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.getArticles().remove(id);
        List<ArticlesEntity> entities = user.getArticles();
        return ArticlesMapper.INSTANCE
                .entityListToArticlesList(entities);
    }

    public List<ArticlesEntity> searchNews(String title) {

        return newsRepo
                .findAll().stream()
                .filter(x -> x.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

}

