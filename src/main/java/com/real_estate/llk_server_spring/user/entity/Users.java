package com.real_estate.llk_server_spring.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime created;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updated;

    public Users() {}

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
