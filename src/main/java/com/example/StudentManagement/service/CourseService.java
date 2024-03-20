package com.example.StudentManagement.service;

import com.example.StudentManagement.domain.Course;
import com.example.StudentManagement.domain.User;
import com.example.StudentManagement.enums.AuthorityEnum;
import com.example.StudentManagement.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Course save(User user) {
        Course course = new Course();
        course.setTeacher(user);

        return courseRepository.save(course);
    }

    public Set<Course> findByUser(User user) {
        // Load courses for teacher
        if (user.getAuthorities()
                .stream().anyMatch(auth -> auth.getAuthority().equals(AuthorityEnum.ROLE_TEACHER))) {
            return courseRepository.findByTeacher(user);
        } else {
            // Load courses for student
            return courseRepository.findByStudents(user);
        }
    }

    public Optional<Course> findById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }
}
