package com.example.SpringLearningProject.service;

import com.example.SpringLearningProject.dto.EmployeeDTO;
import com.example.SpringLearningProject.entity.Employee;
import com.example.SpringLearningProject.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

//    private final EmployeeRepository employeeRepository;
//
//    public EmployeeService(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }

    public EmployeeDTO getEmployee() {
//        return employeeRepository.getEmployee();
        Employee employee = new Employee(12L,"Zulfiqar Ali",28);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        return employeeDTO.EmployeeMapper(employee);
    }
}
