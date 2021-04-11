package com.iot.findneighbor.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Lob;
import java.awt.*;
import java.time.Instant;

@Data
@Builder
//@AllArgsConstructor
public class UserProfile {
    
    private Long id;
    private String name;
    private boolean pets;
    private boolean badHabits;
    private boolean kindOfActivity;
    private String sex;
    private String maritalStatus;
    private int age;
    private boolean haveJobOrJobless;
    private String phoneNumber;
    private String moreAboutUser;

    public UserProfile(Long id, String name, int age, String sex, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
    }

    public UserProfile(Long id, String name, boolean pets, boolean badHabits, boolean kindOfActivity, String sex, String maritalStatus, int age, boolean haveJobOrJobless, String phoneNumber, String moreAboutUser) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPets() {
        return pets;
    }

    public void setPets(boolean pets) {
        this.pets = pets;
    }

    public boolean isBadHabits() {
        return badHabits;
    }

    public void setBadHabits(boolean badHabits) {
        this.badHabits = badHabits;
    }

    public boolean isKindOfActivity() {
        return kindOfActivity;
    }

    public void setKindOfActivity(boolean kindOfActivity) {
        this.kindOfActivity = kindOfActivity;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isHaveJobOrJobless() {
        return haveJobOrJobless;
    }

    public void setHaveJobOrJobless(boolean haveJobOrJobless) {
        this.haveJobOrJobless = haveJobOrJobless;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMoreAboutUser() {
        return moreAboutUser;
    }



    public void setMoreAboutUser(String moreAboutUser) {
        this.moreAboutUser = moreAboutUser;
    }
}
