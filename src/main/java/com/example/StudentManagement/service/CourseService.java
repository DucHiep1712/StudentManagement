package com.example.StudentManagement.service;

import com.example.StudentManagement.domain.Course;
import com.example.StudentManagement.domain.User;
import com.example.StudentManagement.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Course create(User user) {
        Course course = new Course();
        course.setTeacherId(user.getId());

        return courseRepository.save(course);
    }

    public Course update(Course course) {
        return courseRepository.save(course);
    }

    public Set<Course> findByUserId(User user) {
        // Load courses for teacher
        if (user.getAuthorities()
                .stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_TEACHER"))) {
            return courseRepository.findByTeacherId(user.getId());
        } else {
            // Load courses for student
            return courseRepository.findByStudentId(user.getId());
        }
    }

    public Optional<Course> findById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }
}