package com.example.MyDemoProject.repository;

import com.example.MyDemoProject.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {
}
