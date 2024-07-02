package com.real_estate.llk_server_spring.security.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RefreshRepository extends JpaRepository<Refresh, Long> {
    boolean existsByRefresh(String refresh);
    @Transactional
    void deleteByRefresh(String refresh);
}