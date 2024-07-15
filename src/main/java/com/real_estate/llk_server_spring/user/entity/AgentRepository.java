package com.real_estate.llk_server_spring.user.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    Optional<Agent> findByLicenseNumber(String licenseNumber);
}