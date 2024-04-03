package com.example.StudentManagement.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User teacher;
    @ManyToMany
    @JoinTable(name = "course_students", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "students_id"))
    @Fetch(FetchMode.JOIN)
    @JsonManagedReference
    private List<User> students;
    private String name;
    private LocalDate startDate, endDate;
}
