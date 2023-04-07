package com.example.commhere.controller;

import com.example.commhere.dto.UserDTO;
import com.example.commhere.entity.Token;
import com.example.commhere.repository.TokenRepository;
import com.example.commhere.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;
    private final TokenRepository tokenRepository;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody UserDTO addUserDto) {
        return userService.signUp(addUserDto);
    }

    @PostMapping("/sign-in")
    public String signIn(@RequestBody UserDTO loginUserDto) {
        return userService.signIn(loginUserDto);
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAnyRole('USER')") // USER 권한만 호출 가능
    public String hello(@AuthenticationPrincipal UserDTO user) {
        return user.getUserId() + ", 안녕하세요!";
    }

    @PostMapping("logout")
    public String logout(@RequestHeader(name = "Authorization") String header) {
        tokenRepository.save(Token.builder().token(header).build());

        return "success";
    }

}
