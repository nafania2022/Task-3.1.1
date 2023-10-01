package com.springboot.controllers;

import com.springboot.models.User;
import com.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String allUser(Model model) {
        List<User> userList = userService.listAllUser();
        model.addAttribute("users", userList);
        return "index";
    }

    @GetMapping(value = "/add")
    public String addUserForm(Model model){
        model.addAttribute("user", new User());
        return "add";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping( value = "/update/{id}")
    public String updateUserShowForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "update";
    }

    @PostMapping( value = "/update")
    public String updateUser(@ModelAttribute("user") User user) {
        System.out.println(user);
        userService.updateUser(user);
        return "redirect:/";
    }

    @GetMapping( "/remove/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/";
    }
}