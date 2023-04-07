package com.example.commhere.controller;

import com.example.commhere.dto.UserDTO;
import com.example.commhere.entity.Token;
import com.example.commhere.repository.TokenRepository;
import com.example.commhere.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "Auth", description = "인증 기 API")
public class AuthController {

    private final UserService userService;
    private final TokenRepository tokenRepository;

    @Operation(summary = "sign-up", description = "회원가입 API")
    @PostMapping("/sign-up")
    public String signUp(@RequestBody UserDTO addUserDto) {
        return userService.signUp(addUserDto);
    }

    @Operation(summary = "sign-in", description = "로그인 API")
    @PostMapping("/sign-in")
    public String signIn(@RequestBody UserDTO loginUserDto) {
        return userService.signIn(loginUserDto);
    }

    @Operation(summary = "welcome page", description = "USER 권한만 호출 가능한 API")
    @GetMapping("/hello")
    @PreAuthorize("hasAnyRole('USER')") // USER 권한만 호출 가능
    public String hello(@AuthenticationPrincipal UserDTO user) {
        return user.getUserId() + ", 안녕하세요!";
    }

    @Operation(summary = "logout", description = "로그아웃 API")
    @PostMapping("logout")
    public String logout(@RequestHeader(name = "Authorization") String header) {
        tokenRepository.save(Token.builder().token(header).build());

        return "success";
    }

}
