package com.real_estate.llk_server_spring.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginDTO {
    private String email;
    private String password;
}
