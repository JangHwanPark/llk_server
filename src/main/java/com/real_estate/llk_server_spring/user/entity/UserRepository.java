package com.real_estate.llk_server_spring.user.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    boolean existsByUsername(String username);

    Users findByUsername(String username);
}