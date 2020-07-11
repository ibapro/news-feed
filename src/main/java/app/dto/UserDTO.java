package app.dto;

import app.constraints.PasswordMatches;
import app.entity.ArticlesEntity;
import app.restclient.response.Articles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor

@PasswordMatches
public class UserDTO {

    private Integer id;
    @NotBlank(message = "Full Name is empty")
    private String fullName;
    @NotBlank(message = "Email is empty")
    @NotBlank(message = "Email is already exists")
    private String email;
    @Size(message = "Password must contain at least 8 characters", min = 8)
    private String password;
    //@Size(message = "Password must contain at least 8 characters",min = 8)
    @NotBlank(message = "Password does not match")
    private String confirmPassword;

    private Set<Articles> articles = new HashSet<>();


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Set<Articles> getArticles() {
        return articles;
    }

    public void setArticles(Set<Articles> articles) {
        this.articles = articles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
