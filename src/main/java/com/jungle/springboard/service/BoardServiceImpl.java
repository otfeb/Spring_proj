package com.jungle.springboard.service;

import com.jungle.springboard.dto.BoardRequestDto;
import com.jungle.springboard.dto.BoardResponseDto;
import com.jungle.springboard.entity.Board;
import com.jungle.springboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BoardResponseDto> getBoards() {
        return boardRepository.findAllByOrderByCreatedAtDesc()
                .stream().map(BoardResponseDto::of).toList();
    }

    @Override
    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = new Board();
        board.setName(requestDto.getName());
        board.setTitle(requestDto.getTitle());
        board.setContent(requestDto.getContent());
        board.setPassword(requestDto.getPassword());
        boardRepository.save(board);
        return BoardResponseDto.of(board);
    }

    @Transactional
    public BoardResponseDto getBoard(Long id) {
        return boardRepository.findById(id).map(BoardResponseDto::of).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
    }

    @Override
    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto requestDto) throws Exception {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        if (!board.getPassword().equals(requestDto.getPassword()))
            throw new Exception("비밀번호가 일치하지 않습니다.");

        board.setName(requestDto.getName());
        board.setTitle(requestDto.getTitle());
        board.setContent(requestDto.getContent());
        board.update(requestDto);
        return BoardResponseDto.of(board);
    }

    @Override
    @Transactional
    public void deleteBoard(Long id, String password) throws Exception {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        if (!board.getPassword().equals(password))
            throw new Exception("비밀번호가 일치하지 않습니다.");

        boardRepository.deleteById(id);
    }
}
