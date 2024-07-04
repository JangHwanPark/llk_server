package com.real_estate.llk_server_spring.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "companyuser", schema = "db2451506_llk")
public class Companyuser {
    @Id
    @Column(name = "user_id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @Size(max = 50)
    @Column(name = "com_name", length = 50)
    private String comName;

    @Size(max = 100)
    @Column(name = "com_address", length = 100)
    private String comAddress;

    @Size(max = 12)
    @Column(name = "com_phone", length = 12)
    private String comPhone;

    @Column(name = "tax_id")
    private Integer taxId;

}