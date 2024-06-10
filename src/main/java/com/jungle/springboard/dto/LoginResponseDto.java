package com.jungle.springboard.dto;

import com.jungle.springboard.entity.User;
import com.jungle.springboard.entity.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    @NotBlank
    private String username;
    @NotBlank
    private UserRole role;
    @NotBlank
    private String token;

    public static LoginResponseDto of(User user, String token) {
        return LoginResponseDto.builder()
                .username(user.getUsername())
                .role(user.getRole())
                .token(token)
                .build();
    }
}
