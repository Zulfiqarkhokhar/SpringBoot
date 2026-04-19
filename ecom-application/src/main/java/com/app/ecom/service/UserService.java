package com.app.ecom.service;

import com.app.ecom.entity.User;
import com.app.ecom.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void createUser(User user){
        userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
       return userRepository.findById(id);
    }

    public User updateUser(Long id, User user) {
        User oldUser = userRepository.findById(id).orElseThrow();
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        userRepository.save(oldUser);
        return oldUser;
    }
}
