package app.service;

import app.dto.UserDTO;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {

    private final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }


    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("newsfeedinfoteam@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }


    public void sendResetTokenEmail(final String contextPath, final String token, final UserDTO user) {
        final String url = contextPath + "/change-password?token=" + token;
        final String message = "You should follow the link and change your password " + url;
        sendEmail(user.getEmail(), "Reset Password", message);
    }
}

