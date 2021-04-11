package com.iot.findneighbor.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
//@AllArgsConstructor
public class UserSummary {
    private Long id;
    private String username;
    private String name;

    public UserSummary(Long id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }
    private boolean pets;
    private boolean badHabits;
    private boolean kindOfActivity;
    private String sex;
    private String maritalStatus;
    private int age;
    private boolean haveJobOrJobless;
    private String phoneNumber;
    private String moreAboutUser;

    public UserSummary(
            Long id, String username, String name, boolean pets, boolean badHabits, boolean kindOfActivity,
                       String sex, String maritalStatus, int age, boolean haveJobOrJobless, String phoneNumber,
                       String moreAboutUser
    ) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.pets = pets;
        this.badHabits = badHabits;
        this.kindOfActivity = kindOfActivity;
        this.sex = sex;
        this.maritalStatus = maritalStatus;
        this.age = age;
        this.haveJobOrJobless = haveJobOrJobless;
        this.phoneNumber = phoneNumber;
        this.moreAboutUser = moreAboutUser;
    }
}
