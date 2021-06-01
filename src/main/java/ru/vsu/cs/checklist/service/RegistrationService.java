package ru.vsu.cs.checklist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.vsu.cs.checklist.dao.UserDao;
import ru.vsu.cs.checklist.entity.User;
import ru.vsu.cs.checklist.exception.WebException;

import java.nio.charset.Charset;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserDao userDao;

    public User register(String login, String password, String name) {
        if (userDao.existsByLogin(login)) {
            throw new WebException("Login is already used", HttpStatus.BAD_REQUEST);
        }
        String salt = HashUtills.generateSalt(10);
        password = HashUtills.hashPassword(password, salt);
        User user = new User(login, password, salt, name);
        userDao.save(user);
        return user;
    }
}
