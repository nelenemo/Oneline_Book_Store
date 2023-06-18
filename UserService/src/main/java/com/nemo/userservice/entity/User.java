package com.nemo.userservice.entity;

import com.nemo.userservice.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
//    @Column(nullable = false)
    private String username;
//    @Column(nullable = false, unique = true)
    @Email(message = "invalid email format")
    private String email;
//    @Column(nullable = true)
//@Pattern(regexp="^[a-zA-Z0-9]{3}",message="length must be 3")
private String password;
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String contact;
    private String role;
    @Enumerated(EnumType.STRING)
    private Status state;

}

