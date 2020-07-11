package app.service;

import app.dto.UserDTO;
import app.entity.ArticlesEntity;
import app.entity.User;
import app.mapper.ArticlesMapper;
import app.mapper.UserMapper;
import app.repo.UserRepo;
import app.restclient.response.Articles;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public UserDTO persist(UserDTO userDTO) {
        User user = UserMapper.INSTANCE.userDtoToUser(userDTO);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return UserMapper.INSTANCE.userToUserDto(userRepo.save(user));
    }

    public UserDTO addNews(Articles articles) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ArticlesEntity articlesEntity = ArticlesMapper.INSTANCE.articlesToEntity(articles);
        user.getArticles().add(articlesEntity);
        return UserMapper.INSTANCE.userToUserDto(userRepo.save(user));
    }
}
