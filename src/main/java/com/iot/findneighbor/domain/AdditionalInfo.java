package com.iot.findneighbor.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "additional_info")
public class AdditionalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    Long id;

    private boolean pets;
    private boolean badHabits;
    private boolean kindOfActivity;
    private String sex;
    private String maritalStatus;
    private int age;
    private boolean haveJobOrJobless;
    private String phoneNumber;
    private String moreAboutUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;


}
