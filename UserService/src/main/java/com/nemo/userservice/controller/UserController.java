package com.nemo.userservice.controller;

import com.nemo.userservice.dto.UserRequest;
import com.nemo.userservice.dto.UserResponse;
import com.nemo.userservice.entity.User;
import com.nemo.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
     public void addUser(@RequestBody User user){
          userService.addUser(user);

    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> userResponses=userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userResponses);
    }





    @GetMapping("/getById/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable ("userId") int userId){

            UserResponse user = userService.getUserById(userId);

            return ResponseEntity.status(HttpStatus.OK).body(user);
    }


}
