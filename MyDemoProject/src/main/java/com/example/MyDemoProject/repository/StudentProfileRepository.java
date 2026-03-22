package com.example.MyDemoProject.repository;

import com.example.MyDemoProject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentProfileRepository extends JpaRepository<Student,Integer> {
}
