package com.example.StudentManagement.controller;

import com.example.StudentManagement.domain.User;
import com.example.StudentManagement.dto.AuthCredentialsRequest;
import com.example.StudentManagement.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @CrossOrigin
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthCredentialsRequest authCredentialsRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authCredentialsRequest.getUsername(), authCredentialsRequest.getPassword()));

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION,
                            jwtUtil.generateToken(userDetails))
                    .body(userDetails);
        } catch (BadCredentialsException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @CrossOrigin
    @GetMapping("validate")
    public ResponseEntity<?> validateToken(@RequestParam String token, @RequestHeader(HttpHeaders.AUTHORIZATION) String jwt ,@AuthenticationPrincipal User user) {
        try {
            Boolean valid  = jwtUtil.validateToken(token, user);

            return ResponseEntity.ok(valid);
        } catch (ExpiredJwtException exception) {
            return ResponseEntity.ok(false);
        }
    }
}