package com.example.CrudApp.controller;

import com.example.CrudApp.dto.StudentResponseDto;
import com.example.CrudApp.entity.Student;
import com.example.CrudApp.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/")
    public ResponseEntity<String> createStudent(@RequestBody Student student){
        Student std = studentService.createStudent(student);
        return new ResponseEntity<>("Student Created", HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        return new ResponseEntity<>(studentService.getStudentById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id,@RequestBody Student student){
        studentService.updateStudent(id,student);
        return new ResponseEntity<>("Student Updated",HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return new ResponseEntity<>("Student Delete",HttpStatus.OK);
    }
}
