package com.iot.findneighbor.domain;

import lombok.*;

import javax.persistence.*;
import java.awt.*;
import java.io.File;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "user_image")
public class UserImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private File userImage;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;


}
