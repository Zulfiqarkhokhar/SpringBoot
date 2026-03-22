package com.example.MyDemoProject.mapper;

import com.example.MyDemoProject.dto.UserResponseDto;
import com.example.MyDemoProject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "fullName", target = "name")//convert fullname to name
//    @Mapping(target = "age",ignore = true) // will ignore the field age
    UserResponseDto mapUserToUserResponseDto(User user);
}
