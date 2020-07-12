package app.mapper;

import app.entity.ArticlesEntity;
import app.restclient.response.Articles;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;


@Mapper(componentModel = "spring")

public interface ArticlesMapper {
    ArticlesMapper INSTANCE = Mappers.getMapper(ArticlesMapper.class);
    ArticlesEntity articlesToEntity(Articles articles);
    Articles entityToArticles(ArticlesEntity entity);
    List<Articles> entityListToArticlesList(List<ArticlesEntity> entityList);

}
