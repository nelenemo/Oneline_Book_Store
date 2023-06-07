package com.nemo.userservice.service;

import com.nemo.userservice.dto.UserRequest;
import com.nemo.userservice.dto.UserResponse;
import com.nemo.userservice.entity.User;

import java.util.List;

public interface UserService {


    UserResponse getUserById(int userId);

    void addUser(User user);

    List<UserResponse> getAllUsers();
}
