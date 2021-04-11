package com.iot.findneighbor.DAO;

import com.iot.findneighbor.domain.User;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Modifying
    @Query("update User u set u.age = :age where u.username = :username")
    void updateAge(@Param(value = "username") String username, @Param(value = "age") int age);

    @Modifying
    @Query("update User u set u.sex = :sex where u.username = :username")
    void updateSex(@Param(value = "username") String username, @Param(value = "sex") String sex);

    @Modifying
    @Query("update User u set u.pets = :pets where u.username = :username")
    void updatePets(@Param(value = "username") String username, @Param(value = "pets") boolean pets);

    @Modifying
    @Query("update User u set u.badHabits = :badHabits where u.username = :username")
    void updateBadHabit(@Param(value = "username") String username, @Param(value = "badHabits") boolean badHabits);

    @Modifying
    @Query("update User u set u.kindOfActivity = :kindOfActivity where u.username = :username")
    void updateKindOfActivity(@Param(value = "username") String username, @Param(value = "kindOfActivity") boolean kindOfActivity);

    @Modifying
    @Query("update User u set u.maritalStatus = :maritalStatus where u.username = :username")
    void updateMaritalStatus(@Param(value = "username") String username, @Param(value = "maritalStatus") String maritalStatus);

    @Modifying
    @Query("update User u set u.haveJobOrJobless = :haveJobOrJobless where u.username = :username")
    void updateHaveJobOrJobless(@Param(value = "username") String username, @Param(value = "haveJobOrJobless") boolean haveJobOrJobless);

    @Modifying
    @Query("update User u set u.phoneNumber = :phoneNumber where u.username = :username")
    void updatePhoneNumber(@Param(value = "username") String username, @Param(value = "phoneNumber") String phoneNumber);

    @Modifying
    @Query("update User u set u.moreAboutUser = :moreAboutUser where u.username = :username")
    void updateMoreAboutUser(@Param(value = "username") String username, @Param(value = "moreAboutUser") String moreAboutUser);


    @Query("SELECT a.user FROM Address a WHERE a.country = ?1 AND a.city = ?2  AND a.user <> ?3")
    List<User> filterAddress(String country, String city, User user);

    @Query("SELECT a.user FROM Address a WHERE a.country = ?1 AND a.city = ?2  AND a.area = ?3 AND a.user <> ?4")
    List<User> filterArea(String country, String city, String area, User user);

    @Query("select user from AdditionalInfo where sex = ?1")
    List<User> filterSex(String sex);

    @Query("SELECT user from AdditionalInfo  where age between ?1 AND ?2")
    List<User> filterAge(int startAge, int endAge);

}