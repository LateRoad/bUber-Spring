package com.tensionsoft.buber.entity.user;

import com.tensionsoft.buber.entity.Location;
import lombok.*;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class User {
    private Long id;
    private String login;
    private UserRole role;

    private String name;
    private String surname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private Integer tripsNumber;
    private Integer reputation;
    private Location location;

    private Boolean isMuted;
    private Boolean isOnline;

    private UserStatus status;
}