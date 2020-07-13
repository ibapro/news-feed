package app.mapper;

import app.dto.UserDTO;
import app.entity.ArticlesEntity;
import app.entity.User;
import app.restclient.response.Articles;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-14T00:13:39+0400",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 9.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userDtoToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        if ( userDTO.getId() != null ) {
            user.setId( userDTO.getId() );
        }
        user.setFullName( userDTO.getFullName() );
        user.setEmail( userDTO.getEmail() );
        user.setPassword( userDTO.getPassword() );
        user.setArticles( articlesListToArticlesEntityList( userDTO.getArticles() ) );

        return user;
    }

    @Override
    public UserDTO userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setFullName( user.getFullName() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setArticles( articlesEntityListToArticlesList( user.getArticles() ) );
        userDTO.setId( user.getId() );

        return userDTO;
    }

    protected ArticlesEntity articlesToArticlesEntity(Articles articles) {
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

    protected List<ArticlesEntity> articlesListToArticlesEntityList(List<Articles> list) {
        if ( list == null ) {
            return null;
        }

        List<ArticlesEntity> list1 = new ArrayList<ArticlesEntity>( list.size() );
        for ( Articles articles : list ) {
            list1.add( articlesToArticlesEntity( articles ) );
        }

        return list1;
    }

    protected Articles articlesEntityToArticles(ArticlesEntity articlesEntity) {
        if ( articlesEntity == null ) {
            return null;
        }

        Articles articles = new Articles();

        articles.setAuthor( articlesEntity.getAuthor() );
        articles.setTitle( articlesEntity.getTitle() );
        articles.setId( articlesEntity.getId() );
        articles.setDescription( articlesEntity.getDescription() );
        articles.setUrl( articlesEntity.getUrl() );
        articles.setUrlToImage( articlesEntity.getUrlToImage() );
        articles.setPublishedAt( articlesEntity.getPublishedAt() );
        articles.setContent( articlesEntity.getContent() );

        return articles;
    }

    protected List<Articles> articlesEntityListToArticlesList(List<ArticlesEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<Articles> list1 = new ArrayList<Articles>( list.size() );
        for ( ArticlesEntity articlesEntity : list ) {
            list1.add( articlesEntityToArticles( articlesEntity ) );
        }

        return list1;
    }
}
