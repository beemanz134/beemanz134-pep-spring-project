package com.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AccountController {

    @PostMapping("login")
    public String login() {
        return null;
    }

    @PostMapping("register")
    public String register() {
        return null;
    }
}
