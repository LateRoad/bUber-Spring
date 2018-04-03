package com.tensionsoft.buber.service;

import com.tensionsoft.buber.persistence.model.User;
import com.tensionsoft.buber.web.dto.UserDto;
import com.tensionsoft.buber.web.error.UserAlreadyExistException;

public interface IUserService {

    void save(User user);

    User findByUsername(String username);

    User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException;
}
