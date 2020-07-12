package app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Data
@Entity
@Table(name = "news")
@AllArgsConstructor
@NoArgsConstructor
public class ArticlesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String author;
    private String title;
    @Column(length=1000)
    private String description;
    @Column(unique = true)
    private String url;
    private String urlToImage;
    private String publishedAt;
    @Type(type = "text")
    private String content;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_news"
            , joinColumns = { @JoinColumn (name = "articles_id") }
            ,inverseJoinColumns = { @JoinColumn (name = "user_id")})
    private Set<User> users = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticlesEntity that = (ArticlesEntity) o;
        return id == that.id &&
                Objects.equals(author, that.author) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(url, that.url) &&
                Objects.equals(urlToImage, that.urlToImage) &&
                Objects.equals(publishedAt, that.publishedAt) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, description, url, urlToImage, publishedAt, content);
    }
}
