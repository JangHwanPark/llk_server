package com.real_estate.llk_server_spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "unsubscribed_user", schema = "db2451506_llk")
public class UnsubscribedUser {
    @Id
    @ColumnDefault("nextval('db2451506_llk.unsubscribed_user_un_user_id_seq'::regclass)")
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