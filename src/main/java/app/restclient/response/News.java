package app.restclient.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class News {

    @JsonProperty("articles")
    private List<Articles> articles;

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }
}
