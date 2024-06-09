package com.jungle.springboard.controller;

import com.jungle.springboard.dto.BoardRequestDto;
import com.jungle.springboard.dto.BoardResponseDto;
import com.jungle.springboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public List<BoardResponseDto> getAllBoards(){
        return boardService.getBoards();
    }

    @PostMapping
    public BoardResponseDto createPost(@RequestBody BoardRequestDto requestDto) {
        return boardService.createBoard(requestDto);
    }

    @GetMapping("/{id}")
    public BoardResponseDto getPost(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    @PutMapping("/{id}")
    public BoardResponseDto updatePost(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) throws Exception {
        return boardService.updateBoard(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) throws Exception {
        boardService.deleteBoard(id, requestDto.getPassword());
        return "게시글이 삭제되었습니다";
    }
}
