package com.jungle.springboard.service;

import com.jungle.springboard.dto.UserRequestDto;
import com.jungle.springboard.entity.User;
import com.jungle.springboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public Long createUser(UserRequestDto requestDto) {
        validateUser(requestDto);
        User user = new User();
        user.setUsername(requestDto.getUsername());
        user.setPassword(requestDto.getPassword());
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public void validateUser(UserRequestDto requestDto) {
        userRepository.findByUsername(requestDto.getUsername())
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
