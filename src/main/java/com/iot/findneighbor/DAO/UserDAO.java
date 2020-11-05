package com.iot.findneighbor.DAO;

import com.iot.findneighbor.domain.User;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("SELECT a.user FROM Address a WHERE a.country = ?1 AND a.city = ?2  AND a.user <> ?3")
    List<User> filterAddress(String country, String city, User user);

    @Query("SELECT a.user FROM Address a WHERE a.country = ?1 AND a.city = ?2  AND a.area = ?3 AND a.user <> ?4")
    List<User> filterArea(String country, String city, String area, User user);

    @Query("select user from AdditionalInfo where sex = ?1")
    List<User> filterSex(String sex);

    @Query("SELECT user from AdditionalInfo  where age between ?1 AND ?2")
    List<User> filterAge(int startAge, int endAge);

}