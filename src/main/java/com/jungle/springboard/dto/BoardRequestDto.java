package com.jungle.springboard.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
