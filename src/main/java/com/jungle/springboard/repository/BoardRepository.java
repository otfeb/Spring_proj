package com.jungle.springboard.repository;

import com.jungle.springboard.dto.BoardRequestDto;
import com.jungle.springboard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByCreatedAtDesc();
}
