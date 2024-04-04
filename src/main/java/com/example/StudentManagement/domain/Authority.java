package com.example.StudentManagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Table(name = "authority")
@Getter
@Setter
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @OneToMany(mappedBy = "authority",
            fetch = FetchType.EAGER)
    @JsonIgnore
    private List<User> userList;
    @Column(nullable = false, unique = true)
    private String name;

    public Authority() {}

    public Authority(String name) {
        this.name = name;
    }
}