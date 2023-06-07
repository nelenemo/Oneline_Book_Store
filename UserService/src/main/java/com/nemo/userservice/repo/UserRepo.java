package com.nemo.userservice.repo;

import com.nemo.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    @Query("SELECT s FROM User s WHERE s.email=?1")
    Optional<User> findUserByEmail(String email);

    @Query("SELECT u From User u WHERE u.username=?1")
    Optional<User> findUserByUsername(String username);
}
