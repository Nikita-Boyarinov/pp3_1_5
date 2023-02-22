package com.spring.spring_boot.controller;

import com.spring.spring_boot.entity.User;
import com.spring.spring_boot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String getAllUsers(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.getAllUser());

        return "users";
    }


    @GetMapping("/newUser")
    public String getCreationUserForm(ModelMap modelMap) {
        User user = new User();
        modelMap.addAttribute("user", user);
        return "new";
    }

    @PostMapping("/saveUser")
    public String saveNewUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/user/{id}")
    public String getUpdateUserForm(ModelMap modelMap, @PathVariable("id") long id) {
        modelMap.addAttribute("user", userService.getUser(id));
        return "update";
    }
    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id){
        user.setId(id);
        userService.saveUser(user);
    return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable long id){
        userService.deleteUser(id);
        return "redirect:/";
    }
}
