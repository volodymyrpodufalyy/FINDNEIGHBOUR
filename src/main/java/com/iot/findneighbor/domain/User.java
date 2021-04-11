package com.iot.findneighbor.domain;


import com.iot.findneighbor.domain.audit.DateAudit;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "username"
        }),
        @UniqueConstraint(columnNames = {
            "email"
        })
})
public class User  extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String name;

    @NotBlank
    @Size(max = 15)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;

    private boolean pets;
    private boolean badHabits;
    private boolean kindOfActivity;
    private String sex;
    private String maritalStatus;
    private int age;
    private boolean haveJobOrJobless;
    private String phoneNumber;
    private String moreAboutUser;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(){}


    public User(String name, String username, String email, String password, boolean pets, boolean badHabits,
                boolean kindOfActivity, String sex,  String maritalStatus, int age,
                boolean haveJobOrJobless, String phoneNumber, String moreAboutUser
    ) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
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

    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(boolean pets,
                boolean badHabits,
                boolean kindOfActivity,
                String sex,
                String maritalStatus,
                int age,
                boolean haveJobOrJobless,
                String phoneNumber,
                String moreAboutUser) {
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

    public User(int age) {
        super();
    }

    public User(User user) {
        super();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}

