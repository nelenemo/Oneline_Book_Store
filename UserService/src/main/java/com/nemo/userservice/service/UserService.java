package com.nemo.userservice.service;

import com.nemo.userservice.dto.UserRequest;
import com.nemo.userservice.dto.UserResponse;
import com.nemo.userservice.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {


    UserResponse getUserById(int userId);

    void addUser(User user);

    List<UserResponse> getAllUsers();

    List<UserRequest> findAllUsers();
    Optional<User> findUserByEmail(String email);

    String generateToken(String username);
    void validateToken(String token);

    UserResponse getUserByName(String username);
    public int getUserId(String userName);

}
