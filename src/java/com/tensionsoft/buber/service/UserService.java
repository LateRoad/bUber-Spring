package com.tensionsoft.buber.service;

import com.tensionsoft.buber.persistence.dao.RoleRepository;
import com.tensionsoft.buber.persistence.dao.UserRepository;
import com.tensionsoft.buber.persistence.model.Role;
import com.tensionsoft.buber.persistence.model.User;
import com.tensionsoft.buber.web.dto.UserDto;
import com.tensionsoft.buber.web.error.UserAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Override
    public User findByUsername(String login) {
        return userRepository.findByUsername(login);
    }

    @Override
    public User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException {
        if (usernameExist(accountDto.getUsername())) {
            throw new UserAlreadyExistException("There is an account with that username: " + accountDto.getUsername());
        }
        final User user = new User();

        user.setUsername(accountDto.getUsername());
        user.setName(accountDto.getName());
        user.setLastname(accountDto.getLastname());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(1L));
        user.setRoles(roles);
        userRepository.save(user);
    }

    private boolean usernameExist(final String username) {
        return userRepository.findByUsername(username) != null;
    }
}

