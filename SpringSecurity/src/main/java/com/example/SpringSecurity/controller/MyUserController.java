package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.enitity.MyUser;
import com.example.SpringSecurity.repository.MyUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyUserController {

    private final MyUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public MyUserController(MyUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public MyUser saveUser(@RequestBody MyUser myUser){
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        return userRepository.save(myUser);
    }

}
