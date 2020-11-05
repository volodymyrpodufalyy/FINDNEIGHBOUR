package com.iot.findneighbor.service;

import com.iot.findneighbor.DAO.AdditionalInfoDAO;
import com.iot.findneighbor.domain.AdditionalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class AuthService {

    @Autowired
    AdditionalInfoDAO additionalInfoDAO;

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

}
