package com.real_estate.llk_server_spring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "unsubscribed_user", schema = "db2451506_llk")
public class UnsubscribedUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "un_user_id", nullable = false)
    private Long id;

    @Size(max = 20)
    @Column(name = "un_user_first_name", length = 20)
    private String unUserFirstName;

    @Size(max = 20)
    @Column(name = "un_user_last_name", length = 20)
    private String unUserLastName;

    @Size(max = 20)
    @Column(name = "un_user_phone", length = 20)
    private String unUserPhone;

    @Size(max = 400)
    @Column(name = "un_user_inquiries", length = 400)
    private String unUserInquiries;

}