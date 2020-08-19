package com.example.demo.service;

import org.springframework.security.core.Authentication;

public interface AuthService {
    Authentication attemptAuthentication(String username, String password);
}
