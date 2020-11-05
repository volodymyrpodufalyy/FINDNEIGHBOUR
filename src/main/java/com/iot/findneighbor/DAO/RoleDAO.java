package com.iot.findneighbor.DAO;

import com.iot.findneighbor.domain.Role;
import com.iot.findneighbor.domain.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDAO extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
