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
    @Column(name = "user_id")
    private Long id;

    @Column(name = "oauth_username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "user_pw")
    private String userPw;

    @Column(name = "user_phone")
    private String userPhone;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRoles userRoles;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime created;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updated;

    public Users() {}

    public Users(String email, String userPw) {
        this.email = email;
        this.userPw = userPw;
    }

    public Users(String email, UserRoles userRoles) {
        this.email = email;
        this.userRoles = userRoles;
    }
}