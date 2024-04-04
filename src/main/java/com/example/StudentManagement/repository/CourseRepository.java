package com.example.StudentManagement.repository;

import com.example.StudentManagement.domain.Course;
import com.example.StudentManagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface CourseRepository extends JpaRepository<Course, Long> {


    @Query(value = "SELECT * FROM course c INNER JOIN course_students cs WHERE cs.students_id = :studentId AND c.id = cs.course_id", nativeQuery = true)
    Set<Course> findByStudentId(Long studentId);

//    @Query("select * from course where course.teacher_id =" + user.id)
//    @Query("SELECT c FROM Course c WHERE c.teacherId = :teacherid")
    Set<Course> findByTeacherId(Long teacherId);
}
