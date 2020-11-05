package com.iot.findneighbor.DAO;

import com.iot.findneighbor.domain.Preferences;
import com.iot.findneighbor.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.logging.FileHandler;

@Repository
public interface PreferencesDAO extends JpaRepository<Preferences, Long> {
    Optional<Preferences> findByUser(User user);

    Boolean existsByUser(User user);
}
