package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.RoleServiceIml;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceIml;

import java.security.Principal;
import java.util.List;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserServiceIml userServiceIml, RoleServiceIml roleServiceIml) {
        this.userService = userServiceIml;
        this.roleService = roleServiceIml;
    }

    @GetMapping(value = "/admin")
    public String getAllUsers(ModelMap modelMap, Principal principal) {
        List<User> users = userService.getAllUser();
        List<Role> rolesFromBD = roleService.getAllRoles();
        User newUser = new User();
        User user = userService.getUserByEmail(principal.getName());
        users.remove(user);
        modelMap.addAttribute("users", users);
        modelMap.addAttribute("currentUser", user);
        modelMap.addAttribute("rolesFromBD" , rolesFromBD);
        modelMap.addAttribute("newUser" , newUser);
        return "/admin";
    }

    @PostMapping("admin/createUser")
    public String createNewUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("admin/update/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        System.out.println(user);
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/delete/{id}")
    public String deleteUserById(@PathVariable long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
