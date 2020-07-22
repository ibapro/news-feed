package app.dto;

import app.constraints.PasswordResetMatches;

import javax.validation.constraints.Size;

@PasswordResetMatches
public class PasswordDTO {

    @Size(message = "Password must contain at least 8 characters", min = 8)
    private String password;

    @Size(message = "Password must contain at least 8 characters", min = 8)
    private String confirmPassword;

    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
