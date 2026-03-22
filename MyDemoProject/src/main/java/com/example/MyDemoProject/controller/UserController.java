package com.example.MyDemoProject.controller;

import com.example.MyDemoProject.dto.UserResponseDto;
import com.example.MyDemoProject.entity.Student;
import com.example.MyDemoProject.entity.User;
import com.example.MyDemoProject.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/users/{id}")
    public UserResponseDto getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public List<UserResponseDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/mapper/{id}")
    public UserResponseDto getUserByIdWithMapper(@PathVariable Integer id){
        return userService.getUserByIdWithMapper(id);
    }
}
