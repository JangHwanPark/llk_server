package com.real_estate.llk_server_spring.user.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Data
public class JoinDTO {
    private String email;
    private String password;
    private String phone;
}