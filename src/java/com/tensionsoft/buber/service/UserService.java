package com.tensionsoft.buber.service;

import com.tensionsoft.buber.entity.user.User;

public interface UserService {

    void save(User user);

    User findByUsername(String login);

}
