package com.base.mySpring.controller;

import com.base.mySpring.entity.User;
import com.base.mySpring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/myusers")
    public String index(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }


    @GetMapping("/thymeleaf")
    public String leaf(Model model) {
        model.addAttribute("name", "lee eun jeong");
        return "leaf";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/mypage")
    public String mypage() {
        return "mypage";
    }

}
