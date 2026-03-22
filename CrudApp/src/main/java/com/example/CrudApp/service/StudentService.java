package com.example.CrudApp.service;

import com.example.CrudApp.dto.StudentResponseDto;
import com.example.CrudApp.entity.Student;
import com.example.CrudApp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student Not Found"));
    }

    public Student updateStudent(Long id,Student student) {
        Student std = studentRepository.findById(id).orElseThrow(()->new RuntimeException("Student Not Found"));
        
        std.setName(student.getName());
        std.setEmail(student.getEmail());
        studentRepository.save(std);
        return std;

    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
