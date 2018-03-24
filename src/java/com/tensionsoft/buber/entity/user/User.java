package com.tensionsoft.buber.entity.user;

import com.tensionsoft.buber.entity.Location;
import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Transient
    transient private String confirmPassword;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "lastname")
    private String lastname;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "trips_number")
    private Integer tripsNumber;

    @Column(name = "reputation")
    private Integer reputation;

    @Column(name = "location")
    private Location location;

    @Column(name = "is_muted")
    private Boolean isMuted;

    @Column(name = "is_online")
    private Boolean isOnline;

    @Column(name = "status")
    private UserStatus status;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}