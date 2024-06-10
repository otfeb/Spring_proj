package com.jungle.springboard.controller;

import com.jungle.springboard.dto.LoginRequestDto;
import com.jungle.springboard.dto.LoginResponseDto;
import com.jungle.springboard.dto.SignUpRequestDto;
import com.jungle.springboard.entity.User;
import com.jungle.springboard.jwt.JwtUtil;
import com.jungle.springboard.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> createUser(@Valid @RequestBody SignUpRequestDto requestDto) {
        Optional<User> user = userService.createUser(requestDto);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "회원가입 성공");
        response.put("status", HttpStatus.OK.value());

        if (user.isPresent()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("중복된 사용자가 존재합니다.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequestDto requestDto,
                                                  HttpServletResponse response) {
        userService.login(requestDto, response);

        Map<String, Object> responseEntity = new HashMap<>();
        responseEntity.put("message", "로그인 성공");
        responseEntity.put("status", HttpStatus.OK.value());

        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }
}
