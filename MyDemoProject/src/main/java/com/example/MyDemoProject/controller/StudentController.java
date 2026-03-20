package com.example.MyDemoProject.controller;

import com.example.MyDemoProject.entity.Student;
import com.example.MyDemoProject.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }

    @GetMapping("/students")
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public Optional<Student> getStudentById(@PathVariable Integer id){
        return studentRepository.findById(id);
    }

    @GetMapping("/students/search/{firstName}")
    public List<Student> getStudentByName(@PathVariable String firstName){
        return studentRepository.findByFirstNameContaining(firstName);
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable Integer id){
        studentRepository.deleteById(id);
        return "Student Deleted Successfully";
    }

}
