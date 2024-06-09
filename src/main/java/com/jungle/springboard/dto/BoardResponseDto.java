package com.jungle.springboard.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jungle.springboard.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String name;
    private String title;
    private String content;
    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public static BoardResponseDto of(Board board) {
        return BoardResponseDto.builder()
                .id(board.getId())
                .name(board.getName())
                .title(board.getTitle())
                .content(board.getContent())
                .password(board.getPassword())
                .createdAt(board.getCreatedAt())
                .build();
    }

}
