package com.iot.findneighbor.service;

import com.iot.findneighbor.DAO.AdditionalInfoDAO;
import com.iot.findneighbor.DAO.AddressDAO;
import com.iot.findneighbor.DAO.PreferencesDAO;
import com.iot.findneighbor.DAO.UserDAO;
import com.iot.findneighbor.domain.AdditionalInfo;
import com.iot.findneighbor.domain.Address;
import com.iot.findneighbor.domain.Preferences;
import com.iot.findneighbor.domain.User;
import com.iot.findneighbor.exception.ResourceNotFoundException;
import com.iot.findneighbor.exception.UserNotFoundException;
import com.iot.findneighbor.request.UserAllInfoAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CheckUserAvailabilityService {
    @Autowired
    UserDAO userDAO;

    @Autowired
    AdditionalInfoDAO additionalInfoDAO;

    @Autowired
    PreferencesDAO preferencesDAO;

    @Autowired
    AddressDAO addressDAO;

    @Transactional
    public User findUser(Long id) {
        User user = userDAO.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        return user;
    }

    @Transactional
    public Address findAddress(User user) {
        Address address = addressDAO.findByUser(user)
                .orElseThrow(
                        () -> new UserNotFoundException("Address", "user", user)
                );
        return address;
    }

    @Transactional
    public Preferences findPreferences(User user) {
        Preferences preferences = preferencesDAO.findByUser(user)
                .orElseThrow(
                        () -> new UserNotFoundException("Preferences", "user", user)
                );
        return preferences;
    }

    @Transactional
    public AdditionalInfo findAdditional(User user) {
        AdditionalInfo additionalInfo = additionalInfoDAO.findByUser(user)
                .orElseThrow(
                        () -> new UserNotFoundException("Additional info", "user", user)
                );
        return additionalInfo;
    }

    public UserAllInfoAvailability checkHasUserAllInformation(User user){
        boolean isAdditionalInfo = additionalInfoDAO.existsByUser(user);
        boolean isAddress = addressDAO.existsByUser(user);;
        boolean isPreferences = preferencesDAO.existsByUser(user);;
        return new UserAllInfoAvailability(isAdditionalInfo, isAddress, isPreferences);
    }
}
