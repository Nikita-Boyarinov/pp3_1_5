package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceIml;

import java.security.Principal;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = (UserServiceIml) userService;
    }

    @GetMapping(value = "/user")
    public String getAllUsers(ModelMap modelMap, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        modelMap.addAttribute("user", user);
        return "user";
    }

}
