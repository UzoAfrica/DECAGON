package com.activitytracker.activitytracker.service.serviceImpl;

import com.activitytracker.activitytracker.dto.LoginDto;
import com.activitytracker.activitytracker.dto.UserResponseDto;
import com.activitytracker.activitytracker.dto.UserSignUpDto;
import com.activitytracker.activitytracker.exception.UserNotFoundException;
import com.activitytracker.activitytracker.model.User;
import com.activitytracker.activitytracker.repositories.UserRepository;
import com.activitytracker.activitytracker.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<UserResponseDto> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();

        users.forEach(user -> {
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setId(user.getId());
            userResponseDto.setUsername(user.getUsername());
            userResponseDto.setEmail(user.getEmail());
            userResponseDtoList.add(userResponseDto);

        });
        return userResponseDtoList;
    }

    @Override
    public User userSignup(UserSignUpDto userSignUpDto) {
        User user = new User();
        user.setUsername(userSignUpDto.getUsername());
        user.setEmail(userSignUpDto.getEmail());
        user.setPassword(userSignUpDto.getPassword());
        return userRepository.save(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return null;
    }

//    //    @Override
    public UserResponseDto getUser(Long userId){
        User user = userRepository.findById(userId).get();
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }

    //    @Override
    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

    @Override
    public User login(LoginDto loginDto) {
        User user = userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        if(user == null) {
            throw new UserNotFoundException("User with the  credentials not found!");
        } else {
            return user;
        }
    }
}
