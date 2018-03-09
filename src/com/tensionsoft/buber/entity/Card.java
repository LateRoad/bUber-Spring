package com.tensionsoft.buber.entity;

import lombok.*;


@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Card {
    private Long id;
    private String hashNumber;
    private String login;
}