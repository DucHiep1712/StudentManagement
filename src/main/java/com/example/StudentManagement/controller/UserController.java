//package com.example.StudentManagement.controller;
//
//import com.example.StudentManagement.domain.User;
//import com.example.StudentManagement.service.UserDetailsServiceImpl;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/api/users")
//public class UserController {
//    @Autowired
//    UserDetailsServiceImpl userDetailsService;
//
//    @CrossOrigin
//    @GetMapping("")
//    public ResponseEntity<?> getAllUsers(@AuthenticationPrincipal User user) {
//        if ("ROLE_ADMIN".equals(user.getAuthority().getName())){
//            return ResponseEntity.ok(userDetailsService.getAllUsers());
//        } else{
//            return ResponseEntity.ok("Not permitted");
//        }
//    }
//
//    @CrossOrigin
//    @PostMapping("update")
//    public ResponseEntity<?> createUser(@RequestBody User userInfo, @AuthenticationPrincipal User user) {
//        try {
//            if ("ROLE_ADMIN".equals(user.getAuthority().getName())) {
//                userDetailsService.saveUser(userInfo);
//                return ResponseEntity.ok(true);
//            } else {
//                return ResponseEntity.ok(false);
//            }
//        } catch (Exception exception) {
//            return ResponseEntity.ok(false);
//        }
//    }
//
//    @CrossOrigin
//    @DeleteMapping("{userId}")
//    @Transactional
//    public ResponseEntity<?> deleteUser(@PathVariable Long userId, @AuthenticationPrincipal User user) {
//        try {
//            if ("ROLE_ADMIN".equals(user.getAuthority().getName())) {
//                userDetailsService.delete(userId);
//                return ResponseEntity.ok(true);
//            } else {
//                return ResponseEntity.ok(false);
//            }
//        } catch (Exception exception) {
//            return ResponseEntity.ok(false);
//        }
//    }
//}
