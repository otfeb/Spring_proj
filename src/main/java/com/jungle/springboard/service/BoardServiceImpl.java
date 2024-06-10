package com.jungle.springboard.service;

import com.jungle.springboard.dto.BoardRequestDto;
import com.jungle.springboard.dto.BoardResponseDto;
import com.jungle.springboard.entity.Board;
import com.jungle.springboard.entity.User;
import com.jungle.springboard.entity.UserRole;
import com.jungle.springboard.jwt.JwtUtil;
import com.jungle.springboard.repository.BoardRepository;
import com.jungle.springboard.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static com.jungle.springboard.jwt.JwtUtil.AUTHORIZATION_KEY;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BoardResponseDto> getBoards() {
        return boardRepository.findAllByOrderByCreatedAtDesc()
                .stream().map(BoardResponseDto::of).toList();
    }

    @Override
    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto requestDto, HttpServletRequest request) {
        // ContextHolder에서 정보를 받아오는 방법도 있음
        String token = jwtUtil.resolveToken(request);
//        String username = jwtUtil.getUserInfoFromToken(token).get("username", String.class);
        String username = jwtUtil.getUserInfoFromToken(token).getSubject();
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );
        Board board = new Board();
        board.setTitle(requestDto.getTitle());
        board.setContent(requestDto.getContent());
        board.setUser(user);
        Board saveBoard = boardRepository.save(board);
        return BoardResponseDto.of(saveBoard);
    }

    @Transactional
    public BoardResponseDto getBoard(Long id) {
        return boardRepository.findById(id).map(BoardResponseDto::of).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
    }

    @Override
    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto requestDto, HttpServletRequest request) throws Exception {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if(token != null && jwtUtil.validateToken(token)){
            claims = jwtUtil.getUserInfoFromToken(token);

            Board board = boardRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
            );

            if(!board.getUser().getUsername().equals(claims.getSubject())) {
                String roleString = claims.get(AUTHORIZATION_KEY, String.class);
                UserRole role = UserRole.valueOf(roleString);
                if(role != UserRole.ADMIN) {
                    throw new IllegalArgumentException("게시글 작성자만 수정할 수 있습니다.");
                }
            }
            board.update(requestDto);
            return BoardResponseDto.of(board);
        }
        else {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다");
        }
    }

    @Override
    @Transactional
    public void deleteBoard(Long id, HttpServletRequest request) throws Exception {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if(token != null && jwtUtil.validateToken(token)){
            claims = jwtUtil.getUserInfoFromToken(token);

            Board board = boardRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
            );

            if(!board.getUser().getUsername().equals(claims.getSubject())) {
                String roleString = claims.get(AUTHORIZATION_KEY, String.class);
                UserRole role = UserRole.valueOf(roleString);
                if(role != UserRole.ADMIN) {
                    throw new IllegalArgumentException("게시글 작성자만 삭제할 수 있습니다.");
                }
            }
            boardRepository.deleteById(id);
        }
        else {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다");
        }
    }
}
