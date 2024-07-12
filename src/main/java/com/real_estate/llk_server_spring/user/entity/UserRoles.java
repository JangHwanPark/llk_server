package com.real_estate.llk_server_spring.user.entity;

public enum UserRoles {
    ROLE_ADMIN("ROLE_ADMIN"), ROLE_USER("ROLE_USER"), ROLE_AGENT("ROLE_AGENT");

    private final String role;

    UserRoles(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }
}
