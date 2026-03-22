package com.example.MyDemoProject.entity;

import jakarta.persistence.*;

@Entity
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String bio;

    @OneToOne
    @JoinColumn(
            name = "student_id"
    )
    private Student student;

    public StudentProfile() {
    }

    public StudentProfile(String bio) {
        this.bio = bio;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "StudentProfile{" +
                "id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }
}
