package com.example.hospital.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospital.Services.UserService;
import com.example.hospital.dto.UserLoginDto;
import com.example.hospital.dto.UserRegistrationDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity <String> registerUser(@RequestBody UserRegistrationDto userDto) {
        userService.registerUser(userDto);
        return ResponseEntity.ok("User registered successfully as " + userDto.getRole());


    }
    @PostMapping("/login")
    public String loginUser(@RequestBody UserLoginDto userDto) {
      return userService.verify(userDto);
    }


}
