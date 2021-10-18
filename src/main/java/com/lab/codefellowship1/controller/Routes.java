package com.lab.codefellowship1.controller;

import com.lab.codefellowship1.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Routes {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/")
    public String getRoot(){
        return "home";
    }

    @GetMapping("/signup")
    public String getSignupPage(){

        return "signup";
    }

    @GetMapping("/login")
    public String getLoginPage(){

        return "login";
    }

}
