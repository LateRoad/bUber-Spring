package com.tensionsoft.buber.web.dto;

import com.tensionsoft.buber.persistence.model.Role;
import com.tensionsoft.buber.persistence.model.User;
import com.tensionsoft.buber.validation.ValidEmail;
import com.tensionsoft.buber.validation.ValidPassword;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@ToString
@Getter
@Setter
public class UserDto {

    @NotNull
    @Size(min = 8, max = 32, message = "{Size.userDto.username}")
    private String username;

    @ValidPassword
    private String password;

    @NotNull
    @Size(min = 1)
    private String confirmPassword;

    @Size(min = 1, message = "{Size.userDto.name}")
    private String name;

    @Size(min = 1, message = "{Size.userDto.surname}")
    private String surname;

    @Size(min = 1, message = "{Size.userDto.lastname}")
    private String lastname;

    @ValidEmail
    @NotNull
    @Size(min = 1, message = "{Size.userDto.email}")
    private String email;


    private String phoneNumber;


    private Integer tripsNumber;


    private Integer reputation;


//    private Location location;


    private Boolean isMuted;


    private Boolean isOnline;

    private User.Status status;

    private Set<Role> roles;
}
