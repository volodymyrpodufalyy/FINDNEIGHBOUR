package com.iot.findneighbor.controller;

import com.iot.findneighbor.DAO.AddressDAO;
import com.iot.findneighbor.DAO.UserDAO;
import com.iot.findneighbor.domain.Address;
import com.iot.findneighbor.domain.User;
import com.iot.findneighbor.request.UserProfile;
import com.iot.findneighbor.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/filters")
public class FilterController {

    @Autowired
    UserDAO userDAO;

    @Autowired
    AddressDAO addressDAO;

    @Autowired
    FilterService filterService;

    @GetMapping("/user/search")
    public List<UserProfile> filteringByPreference(@RequestParam Long id, @RequestParam  Boolean fullAddress, @RequestParam Boolean sex,
                                                   @RequestParam Boolean age) throws IOException {
        List<User> usersByAddress = new ArrayList<>();
        System.out.println(usersByAddress.size());
        System.out.println("Full adreess " + fullAddress);
        System.out.println("Age " + age);
        System.out.println("Sex " + sex);
        if(fullAddress==false){
            usersByAddress = filterService.filterByAddress(id);
        }
        else{
            usersByAddress = filterService.filterByArea(id);
        }
        if(sex==true){
            usersByAddress = filterService.userFiltrationBySex(id, usersByAddress);
        }
        if(age==true){
          usersByAddress = filterService.userFiltrationByAge(id, usersByAddress);
        }

        List<UserProfile> userProfiles = filterService.setUserProfiles(usersByAddress);

        return userProfiles;
    }



}
