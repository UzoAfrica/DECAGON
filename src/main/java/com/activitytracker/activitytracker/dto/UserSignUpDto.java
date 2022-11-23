package com.activitytracker.activitytracker.dto;

import lombok.Data;

@Data
public class UserSignUpDto {
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
}
