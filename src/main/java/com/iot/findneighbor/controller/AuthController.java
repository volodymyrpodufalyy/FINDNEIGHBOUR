package com.iot.findneighbor.controller;

import com.iot.findneighbor.DAO.*;
import com.iot.findneighbor.domain.*;
import com.iot.findneighbor.exception.AppException;
import com.iot.findneighbor.request.*;
import com.iot.findneighbor.security.JwtTokenProvider;
import com.iot.findneighbor.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(value = {"*"}, exposedHeaders = {"Content-Disposition"})
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    AddressDAO addressDAO;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    AdditionalInfoDAO additionalInfoDAO;

    @Autowired
    UserImageDAO userImageDAO;

    @Autowired
    PreferencesDAO filtersDAO;

    @Autowired
    AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userDAO.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userDAO.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole =
                roleDAO.findByName(RoleName.ROLE_USER)
                        .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userDAO.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/{username}/additionalInfo")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

//    @PostMapping("signup/{username}/additionalInfo")
//    public ResponseEntity<?> registerUserAdditionalInfo(@Valid @RequestBody AdditionalInfo additionalInfoRequest,
//                                                        @PathVariable String username) {
//        System.out.println(additionalInfoRequest.getUser());
//        Optional<User> optional = userDAO.findByUsername(username);
//        User user = optional.isPresent() ? optional.get() : new User();
//        additionalInfoRequest.setUser(user);
//        AdditionalInfo additionalInfo = additionalInfoDAO.save(additionalInfoRequest);
//        User userToGetUsername = additionalInfo.getUser();
//        String userId = userToGetUsername.getUsername();
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentContextPath().path("/signup/{username}/userImage")
//                .buildAndExpand(userId).toUri();
//        return ResponseEntity.created(location).body(new ApiResponse(true, "Additional Info registered successfully"));
//
//    }

    @PutMapping(path = "signup/{username}/additionalInfo")
    public ResponseEntity<?> updateUser(@PathVariable("username") String username,
                                                  @RequestBody User user) {
        authService.updateUser(username,user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/signup/{username}/userImage")
                .buildAndExpand(username).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "Additional Info registered successfully"));
    }

    @RequestMapping (value = "signup/{username}/userImage", method = RequestMethod.POST,
            consumes = {"multipart/form-data"})
    public ResponseEntity<?> registerUserImage(@Valid @ModelAttribute UserImageRequest userImageRequest,
                                                        @PathVariable String username) throws IOException {
        //System.out.println(userImageRequest.getUser());
        Optional<User> optional = userDAO.findByUsername(username);
        User user = optional.isPresent() ? optional.get() : new User();
        System.out.println("User id " + userImageRequest.getImage());

        String destination = "/images/" + username + "/"  + userImageRequest.getImage().getOriginalFilename();
      //  File file = new File(destination);
        authService.createFileFOrStoreImage(username, userImageRequest.getImage());
        //userImageRequest.getImage().transferTo(file);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/signup/{username}/filters")
                .buildAndExpand(username).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "User image registered successfully"));

    }

    @PostMapping("/signup/{username}/filters")
    public ResponseEntity<?> registerUserAddress(@Valid @RequestBody Preferences filtersRequest, @PathVariable String username) {
        Optional<User> optional = userDAO.findByUsername(username);
        User user = optional.isPresent() ? optional.get() : new User();
        filtersRequest.setUser(user);
        Preferences filter = filtersDAO.save(filtersRequest);
        User userToGetUsername = filter.getUser();
        String userId = userToGetUsername.getUsername();
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/signup/{username}/address")
                .buildAndExpand(userId).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "Preferences registered successfully"));
    }

    @PostMapping("/signup/{username}/address")
    public ResponseEntity<?> registerAddress(@Valid @RequestBody Address address, @PathVariable String username) {
        Optional<User> optional = userDAO.findByUsername(username);
        User user = optional.isPresent() ? optional.get() : new User();
        address.setUser(user);
        Address userAddress = addressDAO.save(address);
        User userToGetUsername = userAddress.getUser();
        String userId = userToGetUsername.getUsername();
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/")
                .buildAndExpand(userId).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "Address registered successfully"));

    }
}
