package com.jungle.springboard.service;

import com.jungle.springboard.dto.LoginRequestDto;
import com.jungle.springboard.dto.SignUpRequestDto;
import com.jungle.springboard.entity.User;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public interface UserService {
    public Optional<User> createUser(SignUpRequestDto requestDto);
    public void login(LoginRequestDto requestDto, HttpServletResponse response);
    public void validateUser(SignUpRequestDto requestDto);
}
