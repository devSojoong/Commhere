package com.example.commhere.jwt;

import com.example.commhere.dto.UserDTO;
import com.example.commhere.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 시큐리티 필터 전에 유저 권한이나 인증 관련 정보를 넘겨주는 클래스
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final TokenRepository tokenRepository;


    // doFilterInternal 는 필터의 최상단에 있음.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //프론트에서 header 에 토큰을 달고 요청을 해야함. f12 네트워크 요청 한 부분 확인해보면 heder 에 어떤 부분을 달고 오는지 확인해보면 된다.

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);    // Bearer dsfdjg.fdsghghdsf.fshdgfdgs
//        request.getHeader("Authorization");

        if (!tokenRepository.existsByToken(token)) {


            Claims claims = jwtProvider.tokenToUser(token);

            if (claims != null) {
                UserDTO dto = new UserDTO(claims);

                SecurityContextHolder.getContext()
                        .setAuthentication(new UsernamePasswordAuthenticationToken(
                                dto, null, dto.getAuthorities()));

            }
        }
        filterChain.doFilter(request, response);

    }
}
