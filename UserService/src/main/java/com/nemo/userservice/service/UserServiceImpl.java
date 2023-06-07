package com.nemo.userservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nemo.userservice.dto.UserRequest;
import com.nemo.userservice.dto.UserResponse;
import com.nemo.userservice.entity.User;
import com.nemo.userservice.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service

public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;

    }


    @Override
    public UserResponse getUserById(int userId) {
        User user = userRepo.findById(userId).get();
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setContact(user.getContact());
        return userResponse;
    }

    @Override
    public void addUser(User user) {

        Optional<User> userByEmail = userRepo.findUserByEmail(user.getEmail());
        Optional<User> userByUsername = userRepo.findUserByUsername(user.getUsername());

        if (userByEmail.isPresent() || userByUsername.isPresent()) {
            throw new IllegalStateException("email or username is already in use");
        } else {
            userRepo.save(user);
        }

    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> usersList = userRepo.findAll();
        List<UserResponse> userResponse = Arrays.asList(modelMapper.map(usersList, UserResponse[].class));
        return userResponse;
    }




    }




