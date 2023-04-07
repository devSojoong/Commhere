package com.example.commhere.dto;

import com.example.commhere.entity.User;
import io.jsonwebtoken.Claims;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDTO {
    private String userId;
    private String password;
    private String name;
    private String nickname;
    private String phone;

    public User toEntity() {
        return User.builder()
                .userId(this.userId)
                .password(this.password)
                .name(this.name)
                .nickname(this.nickname)
                .phone(this.phone)
                .build();
    }

    public UserDTO(Claims claims) {
        this.userId = claims.get("userId", String.class);
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        // ROLE_USER, ROLE_ADMIN 은 정해저 있는 네이밍임, getAuthorities() 이름도 같아야함
        // 만약 하나로 구분이 아니고 여러 개의 권한을 가져야 한다면, 아래와 같이 쓸것
        //return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"),new SimpleGrantedAuthority("ROLE_ADMIN"));
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

    }
}