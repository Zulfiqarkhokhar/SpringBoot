package com.example.MyDemoProject.controller;

import com.example.MyDemoProject.entity.School;
import com.example.MyDemoProject.repository.SchoolRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolController {

    private final SchoolRepository schoolRepository;

    public SchoolController(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @PostMapping("/schools")
    public School save(@RequestBody School school){
        return schoolRepository.save(school);
    }

    @GetMapping("/schools")
    public List<School> getAll(){
        return schoolRepository.findAll();
    }
}
