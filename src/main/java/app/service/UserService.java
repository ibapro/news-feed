package app.service;

import app.dto.UserDTO;
import app.entity.ArticlesEntity;
import app.entity.PasswordResetToken;
import app.entity.User;
import app.generator.PasswordGenerator;
import app.mapper.ArticlesMapper;
import app.mapper.UserMapper;
import app.repository.PasswordRepo;
import app.repository.UserRepo;
import app.restclient.response.Articles;
import org.slf4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Logger logger;
    private final PasswordRepo passwordTokenRepository;


    public UserService(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder, Logger logger, PasswordRepo passwordTokenRepository) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.logger = logger;
        this.passwordTokenRepository = passwordTokenRepository;
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

    public void createPasswordResetTokenForUser(final UserDTO userDTO, final String token) {
        final User user = UserMapper.INSTANCE.userDtoToUser(userDTO);
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }

    public Optional<User> getUserByPasswordResetToken(final String token) {
        return Optional.ofNullable(passwordTokenRepository.findByToken(token).getUser());
    }

    public boolean isRegistered(String email) {
        User byEmail = userRepo.findByEmail(email);
        return byEmail != null && email.equals(byEmail.getUsername());
    }

    public UserDTO findByEmail(String email) {
        User byEmail = userRepo.findByEmail(email);
        return UserMapper.INSTANCE.userToUserDto(byEmail);
    }


    /**
     * Previous implementation
     * reset password without token
     * */
    public String changePassword(String email) {
        User byEmail = userRepo.findByEmail(email);
        String password = new String(PasswordGenerator.generatePassword(10));
        byEmail.setPassword(bCryptPasswordEncoder.encode(password));
        userRepo.save(byEmail);
        return password;
    }

    public void changePasswordWithToken(User user, String password) {
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepo.save(user);
    }

}
