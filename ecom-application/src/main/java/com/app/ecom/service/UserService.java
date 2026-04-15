package com.app.ecom.service;

import com.app.ecom.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    List<User> userList = new ArrayList<>();

    public List<User> getAllUsers(){
        return userList;
    }

    public void createUser(User user){
        userList.add(user);
    }

    public User getUserById(Long id) {
       for (User user: userList){
           if(user.getId().equals(id)){
               return user;
           }
       }
        return null;
    }
}
