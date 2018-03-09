package com.tensionsoft.buber.entity;


import lombok.*;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Location {
    private Long id;
    private String latitude;
    private String longitude;
}