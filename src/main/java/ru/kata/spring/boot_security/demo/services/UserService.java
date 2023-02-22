package com.spring.spring_boot.services;


import com.spring.spring_boot.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    User getUser(Long id);

    List<User> getAllUser();


    void deleteUser(Long id);


}
