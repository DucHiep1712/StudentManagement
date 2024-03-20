package com.example.StudentManagement.domain;

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
    @OneToOne
    private User teacher;
    @ManyToMany @Fetch(FetchMode.JOIN)
    private List<User> students;
    private String name;
    private LocalDate startDate, endDate;
}
