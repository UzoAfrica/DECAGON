package com.activitytracker.activitytracker.controller;

import com.activitytracker.activitytracker.dto.LoginDto;
import com.activitytracker.activitytracker.dto.UserResponseDto;
import com.activitytracker.activitytracker.dto.UserSignUpDto;
import com.activitytracker.activitytracker.model.User;
import com.activitytracker.activitytracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody UserSignUpDto userSignUpDto) {
        User user1 = userService.userSignup(userSignUpDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        userService.login(loginDto);
        return new ResponseEntity<>("Your Login is Successfull",HttpStatus.CREATED);
    }


    @GetMapping("/")
    public ResponseEntity<List<UserResponseDto>> getAllUser() {
        List<UserResponseDto> userResponseDtoList = userService.getAllUsers();
        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);

    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long userId) {
        UserResponseDto userResponseDto = userService.getUser(userId);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("user deleted successfully", HttpStatus.NO_CONTENT);
    }
}
