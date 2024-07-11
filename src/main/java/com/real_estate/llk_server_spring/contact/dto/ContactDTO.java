package com.real_estate.llk_server_spring.contact.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ContactDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String description;
}
