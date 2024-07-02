package com.real_estate.llk_server_spring.security.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshRepository extends JpaRepository<Refresh, Long> {
    boolean existsByRefresh(String refresh);

    long deleteByRefresh(String refresh);
}