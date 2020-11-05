package com.iot.findneighbor.controller;

import com.iot.findneighbor.DAO.AdditionalInfoDAO;
import com.iot.findneighbor.DAO.AddressDAO;
import com.iot.findneighbor.DAO.UserDAO;
import com.iot.findneighbor.domain.AdditionalInfo;
import com.iot.findneighbor.domain.User;
import com.iot.findneighbor.exception.UserNotFoundException;
import com.iot.findneighbor.request.UserAllInfoAvailability;
import com.iot.findneighbor.request.UserIdentityAvailability;
import com.iot.findneighbor.request.UserProfile;
import com.iot.findneighbor.request.UserSummary;
import com.iot.findneighbor.security.CurrentUser;
import com.iot.findneighbor.security.UserPrincipal;
import com.iot.findneighbor.service.CheckUserAvailabilityService;
import com.iot.findneighbor.service.FilterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    AddressDAO addressDAO;

    @Autowired
    AdditionalInfoDAO additionalInfoDAO;

    @Autowired
    FilterService filterService;

    @Autowired
    CheckUserAvailabilityService checkUserAvailabilityService;

    @Transactional
    public AdditionalInfo findAdditionalInfo(User user) {
        AdditionalInfo additionalInfo = additionalInfoDAO.findByUser(user)
                .orElseThrow(
                        () -> new UserNotFoundException("Additional info", "user", user)
                );
        return additionalInfo;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        System.out.println("Id " + currentUser.getId());
        System.out.println(currentUser.getUsername());
        System.out.println(currentUser.getName());
        return userSummary;
    }

    @GetMapping("/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userDAO.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userDAO.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }


    @GetMapping("/getUser")
    public User findUserByUsername(@RequestParam(value = "username") String username){
        Optional<User> optionalUser = userDAO.findByUsername(username);
        User user = optionalUser.isPresent() ? optionalUser.get() : new User();
        return user;
    }

    @GetMapping("/userProfileForSearching")
    public UserProfile findAdditionalInfoByUserId(@RequestParam Long userId){
        Optional<User> optionalUser = userDAO.findById(userId);
        User user = optionalUser.isPresent() ? optionalUser.get() : new User();
        AdditionalInfo additionalInfo = findAdditionalInfo(user);
        UserProfile userProfile = new UserProfile(userId, user.getName(), additionalInfo.getAge(), additionalInfo.getSex(),
                additionalInfo.getPhoneNumber());
        return userProfile;
    }

    @GetMapping("/checkUserAvailability")
    public UserAllInfoAvailability checkUserAvailability(@RequestParam Long userId){
        Optional<User> optionalUser = userDAO.findById(userId);
        User user = optionalUser.isPresent() ? optionalUser.get() : new User();
        UserAllInfoAvailability userAllInfoAvailability = checkUserAvailabilityService.checkHasUserAllInformation(user);
        return userAllInfoAvailability;
    }
}