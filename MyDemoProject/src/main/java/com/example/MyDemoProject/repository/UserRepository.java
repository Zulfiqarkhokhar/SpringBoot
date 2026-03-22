package com.example.MyDemoProject.repository;

import com.example.MyDemoProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
