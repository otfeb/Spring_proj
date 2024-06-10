package com.jungle.springboard.service;

import com.jungle.springboard.dto.CustomUserDetails;
import com.jungle.springboard.entity.User;
import com.jungle.springboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userData = userRepository.findByUsername(username);

        if (userData != null) {
            //UserDetails에 담아서 return하면 AutneticationManager가 검증
            return new CustomUserDetails(userData.get());
        }
        return null;
    }
}
