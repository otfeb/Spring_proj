package com.jungle.springboard.service;

import com.jungle.springboard.dto.UserRequestDto;
import com.jungle.springboard.entity.User;

public interface UserService {
    public Long createUser(UserRequestDto requestDto);
    public void validateUser(UserRequestDto requestDto);
}
