package com.nemo.orderservice.util;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {
    private final JwtUtil jwtUtil;

    public AuthUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public String getUserName(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Exclude "Bearer " prefix
            String authorizationKey = authorizationHeader.substring(7);
            return jwtUtil.extractUserName(authorizationKey); // Extract the username

        } else {
            return null;

        }
    }
}