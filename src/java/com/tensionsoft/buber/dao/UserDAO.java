package com.tensionsoft.buber.dao;

import com.tensionsoft.buber.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {
    User findByUsername(String login);
}
