package com.jungle.springboard.controller;

import com.jungle.springboard.dto.BoardRequestDto;
import com.jungle.springboard.dto.BoardResponseDto;
import com.jungle.springboard.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<BoardResponseDto> createPost(@RequestBody BoardRequestDto requestDto, HttpServletRequest request) {
        BoardResponseDto createdBoard = boardService.createBoard(requestDto, request);
        return new ResponseEntity<>(createdBoard, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> getPost(@PathVariable Long id) {
        BoardResponseDto board = boardService.getBoard(id);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> updatePost(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, HttpServletRequest request) throws Exception {
        BoardResponseDto updatedBoard = boardService.updateBoard(id, requestDto, request);
        return new ResponseEntity<>(updatedBoard, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletePost(@PathVariable Long id, HttpServletRequest request) throws Exception {
        boardService.deleteBoard(id, request);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "게시글이 삭제되었습니다");
        response.put("status", HttpStatus.OK.value());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
