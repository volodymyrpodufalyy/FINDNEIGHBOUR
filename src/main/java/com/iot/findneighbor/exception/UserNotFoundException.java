package com.iot.findneighbor.exception;

import com.iot.findneighbor.domain.User;
import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private User fieldValue;

    public UserNotFoundException( String resourceName, String fieldName, User fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
