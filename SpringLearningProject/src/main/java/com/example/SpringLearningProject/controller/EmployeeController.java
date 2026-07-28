package com.example.SpringLearningProject.controller;

import com.example.SpringLearningProject.dto.EmployeeDTO;
import com.example.SpringLearningProject.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/employee")
    public ResponseEntity<EmployeeDTO> getEmployee(){
     EmployeeDTO employeeDTO = employeeService.getEmployee();
     return ResponseEntity.ok(employeeDTO);
    }
}
