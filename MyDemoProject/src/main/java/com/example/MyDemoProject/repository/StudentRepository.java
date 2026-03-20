package com.example.MyDemoProject.repository;

import com.example.MyDemoProject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByFirstNameContaining(String firstName);
}
