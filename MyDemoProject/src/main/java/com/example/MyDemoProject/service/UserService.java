package com.example.MyDemoProject.service;

import com.example.MyDemoProject.dto.UserResponseDto;
import com.example.MyDemoProject.entity.User;
import com.example.MyDemoProject.mapper.UserMapper;
import com.example.MyDemoProject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // manual ways to send response in dto pattern

    public UserResponseDto getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getFullName());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }

    public List<UserResponseDto> getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();

        for(User user:users){
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setId(user.getId());
            userResponseDto.setName(user.getFullName());
            userResponseDto.setEmail(user.getEmail());
            userResponseDtos.add(userResponseDto);
        }
        return userResponseDtos;
    }

    // auto mapping way with mapStruct with no boiler code

    public UserResponseDto getUserByIdWithMapper(Integer id){
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));

        UserResponseDto userResponseDto = userMapper.mapUserToUserResponseDto(user);

        return userResponseDto;
    }


}
