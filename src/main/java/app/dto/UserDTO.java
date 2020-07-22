package app.dto;

import app.constraints.PasswordMatches;
import app.restclient.response.Articles;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

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
    private String email;
    @Size(message = "Password must contain at least 8 characters", min = 8)
    private String password;
    @Size(message = "Password must contain at least 8 characters", min = 8)
    private String confirmPassword;

    private List<Articles> articles = new ArrayList<>();


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

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean passwordMatches(){
        return password.equalsIgnoreCase(confirmPassword);
    }
}
