package com.tensionsoft.buber.dao;

import com.tensionsoft.buber.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, Long> {
}
