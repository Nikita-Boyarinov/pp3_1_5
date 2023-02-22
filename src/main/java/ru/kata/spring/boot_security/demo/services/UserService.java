package ru.kata.spring.boot_security.demo.services;


import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {
    void createUser(User user);

    User getUserById(Long id);

    User getUserByEmail(String email);

    List<User> getAllUser();


    void deleteUser(Long id);


    void updateUser(User user);
}
