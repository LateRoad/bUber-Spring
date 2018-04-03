package com.tensionsoft.buber.persistence.model;

import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class User {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(length = 60)
    private String password;

    @Transient
    private String confirmPassword;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String lastname;

    @Email
    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private Integer tripsNumber;

    @Column
    private Integer reputation;

    @OneToOne
    private Location location;

    @Column
    private Boolean isMuted;

    @Column
    private Boolean isOnline;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;


    public enum Status {
        FREE,
        BUSY,
        INACTIVE
    }
}