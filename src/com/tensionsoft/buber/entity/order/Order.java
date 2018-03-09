package com.tensionsoft.buber.entity.order;


import com.tensionsoft.buber.entity.Location;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Order {
    private Long id;
    private String clientLogin;
    private String driverLogin;
    private String money;
    private OrderStatus status;
    private Date date;
    private Location origin;
    private Location destination;

    public enum OrderStatus {
        DONE,
        ACCEPTED,
        UNDONE,
        CANCELLED;
    }
}
