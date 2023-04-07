package com.example.commhere.jwt;

import com.example.commhere.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider { //토큰을 발급하거나, 토큰을 유저 객체로 바꾸는 클래스

    // 토큰 생성에 필요한 변수값
    private final JwtProperties jwtProperties;
    public String token(User user){
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 3600000))
                .claim("userId", user.getUserId())
                .claim("nickname", user.getNickname())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    // JWT 필터를 타고 security filter 를 타는데 jwt 에서 entity 로 바꿔주고 security filter 를 탈 수 있도록 하기 위해서
    // token 이 null 일수도 있으니 유효성 검사를 해주어야함.
    public Claims tokenToUser(String token){ // "Bearer fsdghfgfdgrfedasfg. esgrdhtsgfdfhg. fgdfhgffgh"
        if(token != null) {
            token = token.substring("Bearer".length());

            return Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token)
                    .getBody();
        }
        return null;

    }

}
