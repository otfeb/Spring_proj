package com.jungle.springboard.service;


import com.jungle.springboard.dto.BoardRequestDto;
import com.jungle.springboard.dto.BoardResponseDto;
import com.jungle.springboard.entity.Board;

import java.util.List;

public interface BoardService {
    public List<BoardResponseDto> getBoards();

    public BoardResponseDto createBoard(BoardRequestDto requestDto);

    public BoardResponseDto getBoard(Long id);

    public BoardResponseDto updateBoard(Long id, BoardRequestDto requestDto) throws Exception;

    public void deleteBoard(Long id, String password) throws Exception;
}
