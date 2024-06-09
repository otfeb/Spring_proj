package com.jungle.springboard.controller;

import com.jungle.springboard.dto.UserRequestDto;
import com.jungle.springboard.entity.User;
import com.jungle.springboard.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String createUser(@Valid @RequestBody UserRequestDto requestDto) {
        userService.createUser(requestDto);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody UserRequestDto requestDto,
                        HttpServletResponse response) {
        return "";
    }
}
