package com.iot.findneighbor.service;

import com.iot.findneighbor.DAO.AdditionalInfoDAO;
import com.iot.findneighbor.DAO.UserDAO;
import com.iot.findneighbor.domain.AdditionalInfo;
import com.iot.findneighbor.domain.User;
import org.bouncycastle.pqc.math.linearalgebra.IntUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    AdditionalInfoDAO additionalInfoDAO;

    @Autowired
    UserDAO userDAO;

    public void createFileFOrStoreImage(String username, MultipartFile file) {
        String wayToFile = username + "_profile_images";
        File newFile = new File(username + "_profile_images");
        if (!newFile.exists()) {
            if (newFile.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
        String orgName = file.getOriginalFilename();

        String filePath = wayToFile + orgName;
        File dest = new File(filePath);
        //check destination exists, if not create it
        if(!dest.exists())
        {
            new File(String.valueOf(dest)).mkdir();
        }
        try {
            file.transferTo(dest);
        }
        catch (IllegalStateException | IOException e)
        {
            e.printStackTrace();
        }
    }

    @Transactional
    public void updateUser(String username, User user) {

        Optional.ofNullable(user.getAge()).ifPresent(age -> userDAO.updateAge(username, age));
        Optional.ofNullable(user.getSex()).ifPresent(sex -> userDAO.updateSex(username, sex));
        Optional.ofNullable(user.isPets()).ifPresent(pets -> userDAO.updatePets(username, pets));

        Optional.ofNullable(user.isBadHabits()).ifPresent(badHabits -> userDAO.updateBadHabit(username, badHabits));
        Optional.ofNullable(user.isKindOfActivity()).ifPresent(kindOfActivity -> userDAO.updateKindOfActivity(username, kindOfActivity));
        Optional.ofNullable(user.getMaritalStatus()).ifPresent(maritalStatus -> userDAO.updateMaritalStatus(username, maritalStatus));

        Optional.ofNullable(user.isHaveJobOrJobless()).ifPresent(haveJobOrJobless -> userDAO.updateHaveJobOrJobless(username, haveJobOrJobless));
        Optional.ofNullable(user.getPhoneNumber()).ifPresent(phoneNumber -> userDAO.updatePhoneNumber(username, phoneNumber));
        Optional.ofNullable(user.getMoreAboutUser()).ifPresent(moreAboutUser -> userDAO.updateMoreAboutUser(username, moreAboutUser));
    }
}
