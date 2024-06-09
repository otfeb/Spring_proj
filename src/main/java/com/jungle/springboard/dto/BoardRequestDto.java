package com.jungle.springboard.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String password;
}
