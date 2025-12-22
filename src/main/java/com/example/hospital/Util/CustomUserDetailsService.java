package com.example.hospital.Util;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.hospital.Repositories.UserRepository;
import com.example.hospital.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("can't find" + email));

                var authorities = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name())) 
            .toList();
            return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            authorities 
    );

       
    }

}
