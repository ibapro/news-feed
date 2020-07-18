package app.service;

import app.dto.UserDTO;
import app.entity.ArticlesEntity;
import app.entity.User;
import app.generator.PasswordGenerator;
import app.mapper.ArticlesMapper;
import app.mapper.UserMapper;
import app.repo.UserRepo;
import app.restclient.response.Articles;
import org.slf4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Logger logger;


    public UserService(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder, org.slf4j.Logger logger) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.logger = logger;
    }

    public UserDTO persist(UserDTO userDTO) {
        User user = UserMapper.INSTANCE.userDtoToUser(userDTO);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return UserMapper.INSTANCE.userToUserDto(userRepo.save(user));
    }

    public UserDTO addNews(Articles articles) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ArticlesEntity articlesEntity = ArticlesMapper.INSTANCE.articlesToEntity(articles);
        user = userRepo.findByEmail(user.getEmail());
        if (!user.getArticles().contains(articlesEntity)) {
            user.getArticles().add(articlesEntity);
        }
        user = userRepo.save(user);
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    public boolean isRegistered(String email) {
        User byEmail = userRepo.findByEmail(email);
        return byEmail != null && email.equals(byEmail.getUsername());
    }


    public String changePassword(String email) {
        User byEmail = userRepo.findByEmail(email);
        String password = new String(PasswordGenerator.generatePassword(10));
        byEmail.setPassword(bCryptPasswordEncoder.encode(password));
        userRepo.save(byEmail);
        return password;
    }


}
