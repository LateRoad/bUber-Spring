package com.tensionsoft.buber.persistence.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "locations")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Location {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String latitude;

    @Column
    private String longitude;
}