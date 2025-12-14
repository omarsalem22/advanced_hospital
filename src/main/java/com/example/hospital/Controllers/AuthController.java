package com.example.hospital.Controllers;

import java.net.Authenticator;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospital.Services.UserService;
import com.example.hospital.Util.JwtUtil;
import com.example.hospital.dto.UserLoginDto;
import com.example.hospital.dto.UserRegistrationDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
  private final UserService userService;
  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDto userDto) {
    userService.registerUser(userDto);
    return ResponseEntity.ok("User registered successfully as " + userDto.getRole());

  }

  @PostMapping("/login")
  public ResponseEntity<String> loginUser(@RequestBody UserLoginDto userDto) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
    String token = jwtUtil.generateToken(authentication.getName());

    return ResponseEntity.ok(token);
  }
}