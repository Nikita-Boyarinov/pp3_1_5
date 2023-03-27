package ru.kata.spring.boot_security.demo.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.ArrayList;



@Component
public class initDataBase {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public initDataBase(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");

        if (!roleService.existRole(roleAdmin)){
            roleService.createRole(roleAdmin);
        }else {
            roleAdmin = roleService.getRole(1L);
        }
        if (!roleService.existRole(roleUser)){
            roleService.createRole(roleUser);
        }else {
            roleUser = roleService.getRole(2L);
        }

        ArrayList<Role> roleUser1 = new ArrayList<>();
        ArrayList<Role> roleUser2 = new ArrayList<>();
        roleUser1.add(roleAdmin);
        roleUser2.add(roleUser);


        User user1 = new User("Admin", 10, "Admin", "admin@mail.ru", "admin", roleUser1);
        User user2 = new User("User", 10, "User", "user@mail.ru", "user", roleUser2);

        if (!userService.existUser(user1)){
            userService.createUser(user1);
        }
        if (!userService.existUser(user2)){
            userService.createUser(user2);
        }

    }
}