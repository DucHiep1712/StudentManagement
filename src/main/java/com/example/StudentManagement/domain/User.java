package com.example.StudentManagement.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Table(name="users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    @ManyToMany
    private ArrayList<Course> courses = new ArrayList<Course>();
}
