package com.example.StudentManagement.domain;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "teacher_id")
    private Long teacherId;
    @ManyToMany
    @JoinTable(name = "course_students", uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "students_id"}), joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "students_id"))
    @Fetch(FetchMode.JOIN)
//    @JsonManagedReference
    private List<User> students;
    private String name;
    private LocalDate startDate, endDate;
}