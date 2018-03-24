package com.tensionsoft.buber.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cars")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "car_number")
    private String carNumber;

    @Column(name = "driver_login")
    private String driverLogin;
}
