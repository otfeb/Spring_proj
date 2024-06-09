package com.jungle.springboard.entity;

import com.jungle.springboard.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String password;

    @CreatedDate
    private LocalDateTime createdAt;

    public void update(BoardRequestDto boardRequestDto){
        this.name = boardRequestDto.getName();
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.password = boardRequestDto.getPassword();
    }

}
