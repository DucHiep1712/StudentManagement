package com.example.StudentManagement.controller;

import com.example.StudentManagement.domain.Course;
import com.example.StudentManagement.domain.User;
import com.example.StudentManagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @CrossOrigin
    @GetMapping("")
    public ResponseEntity<?> getCourse(@AuthenticationPrincipal User user) {
        Set<Course> coursesByUser = courseService.findByUser(user);

        return ResponseEntity.ok(coursesByUser);
    }

    @CrossOrigin
    @GetMapping("{courseId}")
    public ResponseEntity<?> getCourse(@PathVariable Long courseId, @AuthenticationPrincipal User user) {
        Optional<Course> courseOptional = courseService.findById(courseId);

        return ResponseEntity.ok(courseOptional.orElse(new Course()));
    }

    @CrossOrigin
    @PostMapping("")
    public ResponseEntity<?> createCourse(@AuthenticationPrincipal User user) {
        Course newCourse = courseService.save(user);

        return ResponseEntity.ok(newCourse);
    }

    @CrossOrigin
    @PutMapping("{courseId}")
    public ResponseEntity<?> createCourse(@PathVariable Long courseId, @RequestBody Course course, @AuthenticationPrincipal User user) {
        Course updatedCourse = courseService.save(course);

        return ResponseEntity.ok(updatedCourse);
    }
}
