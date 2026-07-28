package com.example.SpringLearningProject.repository;

import com.example.SpringLearningProject.dto.EmployeeDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository {
    EmployeeDTO getEmployee();
}
