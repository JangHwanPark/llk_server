package com.real_estate.llk_server_spring.user.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<Users, Long> {
    boolean existsByEmail(String email);

    Users findByEmail(String email);

    Users findByUsername(String username);

    @Transactional
    @Modifying
    @Query("update Users u set u.userRoles = ?1 where u.email = ?2")
    int updateUserRolesByEmail(@NonNull UserRoles userRoles, @NonNull String email);
}