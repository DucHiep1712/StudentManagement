package com.example.StudentManagement.service;

import com.example.StudentManagement.domain.Course;
import com.example.StudentManagement.domain.User;
import com.example.StudentManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByUsername(username);

        return userOpt.orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));
    }

    public User findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        return user.orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    public Set<User> findUsersByAuthority(String authority) {
        List<User> students = userRepository.findUsersByAuthority(authority);

        return new HashSet<>(students);
    }

    public Set<User> getAllUsers() {
        return new HashSet<>(userRepository.findAll());
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }
}