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
@AllArgsConstructor
public class UserProfile {
    private Long id;
    private String name;
    private int age;
    private String sex;
    private String phoneNumber;

}
