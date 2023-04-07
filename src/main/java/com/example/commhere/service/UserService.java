package com.example.commhere.service;

import com.example.commhere.dto.UserDTO;
import com.example.commhere.entity.User;
import com.example.commhere.jwt.JwtProvider;
import com.example.commhere.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public String signUp(UserDTO userDTO){
        if(userRepository.existsById(userDTO.getUserId())) return userDTO.getUserId() + " 는 이미 존재하는 아이디 입니다.";
        String encodingPassword = encodingPassword(userDTO.getPassword());
        userDTO.setPassword(encodingPassword);
        userRepository.save(userDTO.toEntity());
        return "회원가입이 완료되었습니다.";
    }

    public String signIn(UserDTO userDTO){
        User result = userRepository.findById(userDTO.getUserId()).orElseThrow(IllegalArgumentException::new);
        try {
            if(result == null || !passwordMustBeSame(userDTO.getPassword(), result.getPassword())) throw new IllegalArgumentException();
            return jwtProvider.token(result);
        } catch (NoSuchFieldError e){
            return "failed";
        }
    }

    private String encodingPassword(String password) {
        return passwordEncoder.encode(password);
    }

    private boolean passwordMustBeSame(String requestPassword, String password) {
        if (!passwordEncoder.matches(requestPassword, password)) {
            throw new IllegalArgumentException();
        }
        return true;
    }
}
