package com.iot.findneighbor.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserAllInfoAvailability {
    boolean additionalInfoIsAvailable;
    boolean addressIsAvailable;
    boolean preferencesIsAvailable;
}
