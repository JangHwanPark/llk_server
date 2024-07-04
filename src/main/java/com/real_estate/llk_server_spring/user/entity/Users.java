package com.real_estate.llk_server_spring.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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

    @Size(max = 40)
    @Column(name = "email", length = 40)
    private String email;

    @Size(max = 100)
    @Column(name = "user_pw", length = 100)
    private String userPw;

    @Size(max = 12)
    @Column(name = "user_phone", length = 12)
    private String userPhone;

    @Size(max = 20)
    @Column(name = "user_role", length = 20)
    private String userRole;

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
}