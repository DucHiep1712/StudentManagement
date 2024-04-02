package com.example.StudentManagement.controller;

import com.example.StudentManagement.domain.Course;
import com.example.StudentManagement.domain.User;
import com.example.StudentManagement.service.CourseService;
import com.example.StudentManagement.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

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
    @GetMapping("{courseId}/students")
    public ResponseEntity<?> getUnenrolledStudents(@PathVariable Long courseId, @AuthenticationPrincipal User user) {
        Course course = courseService.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course not found"));
        Set<User> studentSet = userDetailsService.findUsersByAuthority("ROLE_STUDENT");

        List<User> unenrolledStudents = new ArrayList<>();

        for (User student: studentSet) {
            if (!(course.getStudents().contains(student))) {
                System.out.println(student.getId());
                unenrolledStudents.add(student);
            }
        }

        return ResponseEntity.ok(unenrolledStudents);
    }

    @CrossOrigin
    @PostMapping("")
    public ResponseEntity<?> createCourse(@AuthenticationPrincipal User user) {
        Course newCourse = courseService.save(user);

        return ResponseEntity.ok(newCourse);
    }

    @CrossOrigin
    @PostMapping("{courseId}/addStudent")
    public ResponseEntity<?> addStudent(@PathVariable Long courseId, @RequestBody Long studentId, @AuthenticationPrincipal User user) {
        User student = userDetailsService.findById(studentId);
        Course course = courseService.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course not found"));

        course.getStudents().add(student);
        courseService.save(course);

        return ResponseEntity.ok(course);
    }

    @CrossOrigin
    @PutMapping("{courseId}")
    public ResponseEntity<?> createCourse(@PathVariable Long courseId, @RequestBody Course course, @AuthenticationPrincipal User user) {
        Course updatedCourse = courseService.save(course);

        return ResponseEntity.ok(updatedCourse);
    }

    @CrossOrigin
    @PostMapping("{courseId}/removeStudent")
    public ResponseEntity<?> removeStudent(@PathVariable Long courseId, @RequestBody Long studentId, @AuthenticationPrincipal User user) {
        User student = userDetailsService.findById(studentId);
        Course course = courseService.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course not found"));

        course.getStudents().remove(student);
        courseService.save(course);

        return ResponseEntity.ok(course);
    }

    @CrossOrigin
    @DeleteMapping("{courseId}/removeStudent")
    public ResponseEntity<?> removeStudent(@PathVariable Long courseId,  @AuthenticationPrincipal User user) {
        User student = userDetailsService.findById(user.getId());
        Course course = courseService.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course not found"));

        course.getStudents().remove(student);
        courseService.save(course);

        return ResponseEntity.ok("Leave course successfully");
    }
}
