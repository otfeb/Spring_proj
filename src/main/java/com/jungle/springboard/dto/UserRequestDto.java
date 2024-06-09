package com.jungle.springboard.dto;

import com.jungle.springboard.entity.User;
import com.jungle.springboard.entity.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Size(min = 4, max = 10, message = "아이디는 4자 이상 10자 이하여야 합니다.")
    @Pattern(regexp = "^[a-z0-9]+$", message = "아이디는 알파벳 소문자와 숫자만 사용 가능합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 8, max = 15, message = "비밀번호는 8자 이상 15자 이하여야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "비밀번호는 알파벳 대소문자와 숫자만 사용 가능합니다.")
    private String password;

    // 비밀번호 암호화 X
    public User toEntity(){
        return User.builder()
                .username(this.username)
                .password(this.password)
                .role(UserRole.USER)
                .build();
    }

    // 비밀번호 암호화
    public User toEntity(String encodedPassword){
        return User.builder()
                .username(this.username)
                .password(encodedPassword)
                .role(UserRole.USER)
                .build();
    }
}
