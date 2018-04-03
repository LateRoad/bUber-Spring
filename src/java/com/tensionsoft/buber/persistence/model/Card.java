package com.tensionsoft.buber.persistence.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cards")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Card {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String hashNumber;

    @Column
    private String lastDigits;

    @Column
    private String login;
}