package com.thegiftgarden.controller;

import com.thegiftgarden.model.User;
import com.thegiftgarden.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    // REPOSITORIES
    UserRepository userRepository;


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

    @PostMapping("/createuser")
    public String createUser(
            @RequestParam("firstName") String newFirstName,
            @RequestParam("lastName") String newLastName,
            @RequestParam("email") String newEmail,
            @RequestParam("password") String newPassword){

         User newUser = new User();

         newUser.setFirstName(newFirstName);
         newUser.setLastName(newLastName);
         newUser.setEmail(newEmail);
         newUser.setPassword(newPassword);

         userRepository.addUser(newUser);




        return "redirect:/login";
    }




}
