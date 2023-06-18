package com.nemo.orderservice.feignClient;

import com.nemo.orderservice.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "user-service", path = "/auth")
public interface UserService {
    @GetMapping("/getById/{userId}")
   ResponseEntity<UserResponse> getUserById(@PathVariable ("userId") int userId);

    @GetMapping("/getByName/{username}")
    ResponseEntity<UserResponse> getUserByName(@PathVariable ("username") String username);

    @GetMapping("/getUserId/{userName}")
    public int getUserId(@PathVariable String userName) ;

}
















//    @GetMapping("/getUserId/{userName}")
//    Long getUserId(@PathVariable String userName);
//
//    @GetMapping("/getUserByName/{name}")
//    UserResponse getUserByName(@PathVariable String name);