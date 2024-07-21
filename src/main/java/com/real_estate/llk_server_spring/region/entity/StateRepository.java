package com.real_estate.llk_server_spring.region.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Long> {
    boolean existsByStateName(String stateName);

    boolean existsByStateAbbreviation(String stateAbbreviation);

    @Transactional
    @Modifying
    @Query("update State s set s.stateName = ?1, s.stateAbbreviation = ?2 where s.id = ?3")
    void updateStateNameAndStateAbbreviationById(String stateName, String stateAbbreviation, Long id);

    List<State> findAllByOrderById();
}