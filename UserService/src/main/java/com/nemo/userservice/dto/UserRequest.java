package com.nemo.userservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
//  @NotEmpty
    private String firstName;
//  @NotEmpty
    private String lastName;
//  @NotEmpty(message = "Please write down valid email id")
    private String email;
//  @NotEmpty(message = "Password is complusory")
    private String password;
    private String contact;
    private String role;


}
