package com.iot.findneighbor.domain;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "filters")
public class Preferences {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int startAge;
    private int endAge;
    private String sex;
    private String pets;
    private String badHabits;
    private int startPrice;
    private int endPrice;
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;
}
