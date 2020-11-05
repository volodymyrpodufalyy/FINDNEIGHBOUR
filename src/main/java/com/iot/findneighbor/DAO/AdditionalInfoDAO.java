package com.iot.findneighbor.DAO;

import com.iot.findneighbor.domain.AdditionalInfo;
import com.iot.findneighbor.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdditionalInfoDAO extends JpaRepository<AdditionalInfo, Long> {

    Optional<AdditionalInfo> findByUser(User user);

    Boolean existsByUser(User user);
}
