package com.example.StudentManagement.repository;

import com.example.StudentManagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u JOIN u.authority a WHERE a.name = :role")
    List<User> findUsersByAuthority(@Param("role") String role);

//    @Query(value = "DELETE FROM course_students WHERE students_id = :userId;" +
//            "DELETE FROM course WHERE teacher_id = :userId;" +
//            "DELETE FROM authority WHERE user_id = :userId;" +
//            "DELETE FROM users WHERE id = :userId", nativeQuery = true)
//    void deleteUserById(@Param("userId") Long userId);
}
