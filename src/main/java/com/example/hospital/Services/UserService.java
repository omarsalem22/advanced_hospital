package com.example.hospital.Services;

import java.util.Collections;
import java.util.HashSet;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hospital.Repositories.UserRepository;
import com.example.hospital.dto.UserRegistrationDto;
import com.example.hospital.entity.User;
import com.example.hospital.enums.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(UserRegistrationDto user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already taken");
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPhoneNumber(user.getPhoneNumber());
        // encode password
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = Role.PATIENT;
        if (user.getRole() != null) {
            try {
                String rolename = user.getRole().toUpperCase();
                role = Role.valueOf(rolename);
            } catch (Exception e) {
                throw new RuntimeException("Invalid Role provided");
            }

        }
        newUser.setRoles(new HashSet<>(Collections.singletonList(role)));
        return userRepository.save(newUser);

    }

}
