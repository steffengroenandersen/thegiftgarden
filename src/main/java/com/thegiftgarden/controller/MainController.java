package com.thegiftgarden.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // HOMEPAGE
    @GetMapping("/")
    public String homepage(){
        return "homepage";
    }

    // LOGIN
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    // CREATE ACCOUNT
    @GetMapping("/createaccount")
    public String createAccount(){
        return "createaccount";
    }

}
