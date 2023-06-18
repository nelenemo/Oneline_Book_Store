package com.nemo.userservice.config;

import com.nemo.userservice.entity.User;
import com.nemo.userservice.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    public UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<User> user= userRepo.findUserByUsername(username);

        return user.map(CustomUserDetails::new).orElseThrow(
                ()-> new UsernameNotFoundException(username+"not found")
        );
    }
}
