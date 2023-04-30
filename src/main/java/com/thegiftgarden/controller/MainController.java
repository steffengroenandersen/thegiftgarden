package com.thegiftgarden.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.thegiftgarden.model.User;
import com.thegiftgarden.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Controller
public class MainController {

    // REPOSITORIES
    UserRepository userRepository;
    
    // Constructor for MainController
    public MainController(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    // HOMEPAGE ///////////////////////////////////////
    @GetMapping("/")
    public String homepage(){
        return "homepage";
    }

    // LOGIN //////////////////////////////////////////
    @GetMapping("/login")
    public String login(Model model){
        // CREATE NEW USER
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/loginuser")
    public String loginUser(
            @RequestParam() String checkEmail,
            @RequestParam() String checkPassword,
            Model model,
            HttpSession session){
        
        User user = new User();
        model.addAttribute("user", user);

        // Check if user exits
        List<User> userList = userRepository.getAllUsers();
        for(User checkUser : userList){
            String actualEmail = checkUser.getEmail();
            String actualPassword = checkUser.getPassword();

            // If the user exists...
            if(checkEmail.equals(actualEmail) && checkPassword.equals(actualPassword)){
                // Add the user to the session and model
                model.addAttribute("currentUser", checkUser);
                session.setAttribute("currentUser", checkUser);
                
                User currentUser = (User) session.getAttribute("currentUser");
                System.out.println(currentUser);
                
                // Redirect to the new page
                return "redirect:/garden/" + checkUser.getUserID();
            
            }


        }

        return "redirect:/garden/";
    }

    // CREATE ACCOUNT //////////////////////////////////
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

    @GetMapping("/garden")
    public String garden(){
        return "garden";
    }





}
