package com.example.SpringLearningProject.dto;

import com.example.SpringLearningProject.entity.Employee;

public class EmployeeDTO {

    private Long id;
    private String name;
    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public EmployeeDTO EmployeeMapper(Employee employee){

        setId(employee.getId());
        setName(employee.getName());
        setAge(employee.getAge());
        return this;
    }
}
