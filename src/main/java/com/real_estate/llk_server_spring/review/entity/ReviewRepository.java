package com.real_estate.llk_server_spring.review.entity;

import com.real_estate.llk_server_spring.user.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByAgent(Agent agent);
}