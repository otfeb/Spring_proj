package com.jungle.springboard.service;


import com.jungle.springboard.dto.BoardRequestDto;
import com.jungle.springboard.dto.BoardResponseDto;
import com.jungle.springboard.entity.Board;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface BoardService {
    public List<BoardResponseDto> getBoards();

    public BoardResponseDto createBoard(BoardRequestDto requestDto, HttpServletRequest request);

    public BoardResponseDto getBoard(Long id);

    public BoardResponseDto updateBoard(Long id, BoardRequestDto requestDto, HttpServletRequest request) throws Exception;

    public void deleteBoard(Long id, HttpServletRequest request) throws Exception;
}
