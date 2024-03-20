package com.example.StudentManagement.repository;

import com.example.StudentManagement.domain.Course;
import com.example.StudentManagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("select * from course")
    Set<Course> findByStudents(User user);

//    @Query("select * from course where course.teacher_id =" + user.id)
    Set<Course> findByTeacher(User user);
}
