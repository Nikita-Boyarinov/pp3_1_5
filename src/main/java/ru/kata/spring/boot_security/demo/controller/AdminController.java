package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
        users.remove(userService.getUserByEmail(principal.getName()));
        modelMap.addAttribute("users", users);
        return "admin/users";
    }

    @GetMapping("/admin/newUser")
    public String getCreationUserForm(ModelMap modelMap) {
        User user = new User();
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("roles", roleService.getAllRoles());
        return "admin/new";
    }

    @PostMapping("admin/createUser")
    public String createNewUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/user/{id}")
    public String getUpdateUserForm(ModelMap modelMap, @PathVariable("id") long id) {
        modelMap.addAttribute("user", userService.getUserById(id));
        modelMap.addAttribute("roles", roleService.getAllRoles());
        return "admin/update";
    }

    @PatchMapping("admin/update/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/delete/user/{id}")
    public String deleteUserById(@PathVariable long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
