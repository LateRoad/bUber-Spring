package com.tensionsoft.buber.persistence.model;


import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Order {

    @Id
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    private String clientLogin;

    @Column
    private String driverLogin;

    @Column
    private String money;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private Date date;

    @OneToOne
    private Location origin;

    @OneToOne
    private Location destination;

    public enum Status {
        DONE,
        ACCEPTED,
        UNDONE,
        CANCELLED;
    }
}
