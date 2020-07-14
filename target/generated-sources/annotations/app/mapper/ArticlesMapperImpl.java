package app.mapper;

import app.entity.ArticlesEntity;
import app.restclient.response.Articles;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-14T17:27:38+0400",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 13.0.3 (Azul Systems, Inc.)"
)
@Component
public class ArticlesMapperImpl implements ArticlesMapper {

    @Override
    public ArticlesEntity articlesToEntity(Articles articles) {
        if ( articles == null ) {
            return null;
        }

        ArticlesEntity articlesEntity = new ArticlesEntity();

        articlesEntity.setId( articles.getId() );
        articlesEntity.setAuthor( articles.getAuthor() );
        articlesEntity.setTitle( articles.getTitle() );
        articlesEntity.setDescription( articles.getDescription() );
        articlesEntity.setUrl( articles.getUrl() );
        articlesEntity.setUrlToImage( articles.getUrlToImage() );
        articlesEntity.setPublishedAt( articles.getPublishedAt() );
        articlesEntity.setContent( articles.getContent() );

        return articlesEntity;
    }

    @Override
    public Articles entityToArticles(ArticlesEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Articles articles = new Articles();

        articles.setAuthor( entity.getAuthor() );
        articles.setTitle( entity.getTitle() );
        articles.setId( entity.getId() );
        articles.setDescription( entity.getDescription() );
        articles.setUrl( entity.getUrl() );
        articles.setUrlToImage( entity.getUrlToImage() );
        articles.setPublishedAt( entity.getPublishedAt() );
        articles.setContent( entity.getContent() );

        return articles;
    }

    @Override
    public List<Articles> entityListToArticlesList(List<ArticlesEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<Articles> list = new ArrayList<Articles>( entityList.size() );
        for ( ArticlesEntity articlesEntity : entityList ) {
            list.add( entityToArticles( articlesEntity ) );
        }

        return list;
    }
}
