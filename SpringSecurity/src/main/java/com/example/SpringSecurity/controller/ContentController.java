package com.example.SpringSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentController {

    @GetMapping("/home")
    public String HandleWelcome(){
        return "Home";
    }

    @GetMapping("/admin/home")
    public String HandleAdminHome(){
        return "Admin_Home";
    }

    @GetMapping("/user/home")
    public String HandleUserHome(){
        return "User_Home";
    }

}
