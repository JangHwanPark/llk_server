package com.real_estate.llk_server_spring.region.dto;

import lombok.*;

@Getter @Setter
public class UpdateStateDTO {
    private Long stateId;
    private String stateName;
    private String stateAbbreviation;
}
