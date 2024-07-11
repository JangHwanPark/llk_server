package com.real_estate.llk_server_spring.entity;

import com.real_estate.llk_server_spring.user.entity.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "individualuser", schema = "db2451506_llk")
public class Individualuser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @Size(max = 20)
    @Column(name = "user_first_name", length = 20)
    private String userFirstName;

    @Size(max = 20)
    @Column(name = "user_last_name", length = 20)
    private String userLastName;

}