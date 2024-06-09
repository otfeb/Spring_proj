package com.jungle.springboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/form")
    public String writePost() {
        return "newPost";
    }

    @GetMapping("/posts")
    public String postList(){
        return "posts";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }
}
