package com.iot.findneighbor.DAO;

import com.iot.findneighbor.domain.User;
import com.iot.findneighbor.domain.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserImageDAO extends JpaRepository<UserImage, Long> {

}
