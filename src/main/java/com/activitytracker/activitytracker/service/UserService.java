package com.activitytracker.activitytracker.service;

import com.activitytracker.activitytracker.dto.LoginDto;
import com.activitytracker.activitytracker.dto.UserResponseDto;
import com.activitytracker.activitytracker.dto.UserSignUpDto;
import com.activitytracker.activitytracker.model.User;

import java.util.List;

public interface UserService {
    User userSignup( UserSignUpDto userSignUpDto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUser(Long userId);
    void deleteUser(Long userId);

    User login(LoginDto loginDto);
}
