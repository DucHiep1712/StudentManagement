package com.example.StudentManagement.repository;

import com.example.StudentManagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u JOIN u.authorities a WHERE a.authority = :role")
    List<User> findUsersByAuthority(@Param("role") String role);
}
