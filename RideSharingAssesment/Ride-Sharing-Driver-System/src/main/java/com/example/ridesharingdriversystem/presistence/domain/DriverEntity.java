package com.example.ridesharingdriversystem.presistence.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "DRIVER")
@Entity
public class DriverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "USER_ID")
    private Long authUserId;

    @Column(name = "STATUS")
    private Integer status;
}
