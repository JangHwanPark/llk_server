package com.real_estate.llk_server_spring.entity;

import com.real_estate.llk_server_spring.Product.Entity.Product;
import com.real_estate.llk_server_spring.user.entity.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "appointment", schema = "db2451506_llk")
public class Appointment {
    @Id
    @ColumnDefault("nextval('db2451506_llk.appointment_appointment_id_seq'::regclass)")
    @Column(name = "appointment_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "appointment_date")
    private LocalDate appointmentDate;

}