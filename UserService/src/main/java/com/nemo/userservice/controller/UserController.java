package com.nemo.userservice.controller;

import com.nemo.userservice.dto.AuthRequest;
import com.nemo.userservice.dto.UserRequest;
import com.nemo.userservice.dto.UserResponse;
import com.nemo.userservice.entity.User;
import com.nemo.userservice.exception.UserNotFound;
import com.nemo.userservice.service.UserService;
import com.nemo.userservice.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserServiceImpl userServiceImpl;
    private final AuthenticationManager authenticationManager;


    private final UserService userService;

    public UserController(UserServiceImpl userServiceImpl, AuthenticationManager authenticationManager, UserService userService) {
        this.userServiceImpl = userServiceImpl;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }




    @PostMapping("/register")
     public void addUser(@RequestBody User user){
          userService.addUser(user);

    }
    @PostMapping("/login")
    public String loginExtractToken(@RequestBody AuthRequest authRequest){
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
        );
        if(authentication.isAuthenticated()){
            return userServiceImpl.generateToken(authRequest.getUsername());
        }else{
            throw new RuntimeException("Invalid Access");
        }

    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return userServiceImpl.generateToken(authRequest.getUsername());
        } else {
            throw new UserNotFound("Incorrect Username or password, couldn't generate  token");
        }
    }


    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        userService.validateToken(token);
        return "Token is valid";
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
    @GetMapping("/getByName/{username}")
    public ResponseEntity<UserResponse> getUserByName(@PathVariable ("username") String username){

        UserResponse user = userService.getUserByName(username);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @GetMapping("/getUserId/{userName}")
    public int getUserId(@PathVariable String userName) {
        return userService.getUserId(userName);
    }


}
