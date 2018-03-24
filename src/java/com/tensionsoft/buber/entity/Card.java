package com.tensionsoft.buber.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cards")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Card {
    private Long id;
    private String hashNumber;
    private String lastDigits;
    private String login;
}