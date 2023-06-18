package com.nemo.userservice.service;

import com.nemo.userservice.dto.UserRequest;
import com.nemo.userservice.dto.UserResponse;
import com.nemo.userservice.entity.Mail;
import com.nemo.userservice.entity.User;
import com.nemo.userservice.enums.Status;
import com.nemo.userservice.exception.UserNotFound;
import com.nemo.userservice.externalService.MailService;
import com.nemo.userservice.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j

public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;


    @Autowired
    private JwtService jwtService;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder, MailService mailService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;


        this.mailService = mailService;
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
            throw new IllegalStateException("Email or username is already in use");
        }
        if (userRepo.count() == 0) {
            // First user being added, set role as "ROLE_ADMIN"
            user.setRole("ROLE_ADMIN");
        } else {
            // Subsequent users, set role as "ROLE_USER"
            user.setRole("ROLE_USER");
        }
        if (user.getRole().equals("ROLE_ADMIN")) {
            user.setState(Status.ACTIVE);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        mail(user.getEmail());
        userRepo.save(user);
    }


    public String generateToken(String userName) {
        String role = userRepo.getUserByRole(userName).orElseThrow(() -> {
            return new UserNotFound("User with this name " + userName + " doesn't exist");
        });
        return jwtService.generateToken(userName, role);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    @Override
    public UserResponse getUserByName(String username) {
        Optional<User> userByUsername = userRepo.findUserByUsername(username);
        UserResponse map = modelMapper.map(userByUsername, UserResponse.class);
        return map;
    }

    @Override
    public int getUserId(String userName) {
        log.info("Fetching user");
        return userRepo.getUserId(userName)
                .orElseThrow(() -> {
                            log.error("user does not exist");
                            return new UserNotFound(
                                    "User with name " + userName + " do not exist");
                        }
                );
    }


    public void mail(String email ) {
        Mail mail1=new Mail();
        mail1.setTo(email);
        mail1.setBody("you have been registered in online book store.");
        mail1.setSubject("Online book store message");
        mailService.mail(mail1);

    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> usersList = userRepo.findAll();
        List<UserResponse> userResponse = Arrays.asList(modelMapper.map(usersList, UserResponse[].class));
        return userResponse;
    }


    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    @Override
    public List<UserRequest> findAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map((user) -> mapToUserRequest(user)).collect(Collectors.toList());
    }

    private UserRequest mapToUserRequest(User user) {
        UserRequest userDto = new UserRequest();
        String[] str = user.getUsername().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }


}







