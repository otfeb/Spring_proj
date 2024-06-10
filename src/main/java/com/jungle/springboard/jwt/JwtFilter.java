package com.jungle.springboard.jwt;

import com.jungle.springboard.dto.CustomUserDetails;
import com.jungle.springboard.entity.User;
import com.jungle.springboard.entity.UserRole;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.jungle.springboard.jwt.JwtUtil.AUTHORIZATION_KEY;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // resolveToken 함수를 사용하여 토큰을 추출
        String token = jwtUtil.resolveToken(request);

        // 토큰 검증
        if (token == null) {
            System.out.println("token null");
            filterChain.doFilter(request, response);

            // 조건이 해당되면 메소드 종료 (필수)
            return;
        }

        System.out.println("authorization now");

        //토큰 유효성 검증
        if (!jwtUtil.validateToken(token)) {

            System.out.println("유효하지 않은 토큰입니다");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료 (필수)
            return;
        }

        //토큰에서 username과 role 획득
        String username = jwtUtil.getUserInfoFromToken(token).getSubject();
        String roleStr = jwtUtil.getUserInfoFromToken(token).get(AUTHORIZATION_KEY, String.class);
        UserRole role = UserRole.valueOf(roleStr);

        //userEntity를 생성하여 값 set
        User user = new User();
        user.setUsername(username);
        // 임의로 지정
        user.setPassword("temppassword");
        user.setRole(role);

        //UserDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}

