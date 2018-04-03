package com.tensionsoft.buber.persistence.model;


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
    @GeneratedValue
    private Long id;

    @Column
    private String carNumber;

    @Column
    private String driverLogin;
}
