package com.example.commhere.service;

import com.example.commhere.dto.FavorDTO;
import com.example.commhere.dto.PasswordDTO;
import com.example.commhere.dto.ReviewDTO;
import com.example.commhere.dto.UserDTO;
import com.example.commhere.entity.Favor;
import com.example.commhere.entity.Review;
import com.example.commhere.entity.User;
import com.example.commhere.jwt.JwtProvider;
import com.example.commhere.repository.FavorRepository;
import com.example.commhere.repository.ReviewRepository;
import com.example.commhere.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final FavorRepository favorRepository;
    private final ReviewRepository reviewRepository;

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

    public UserDTO getUserInfo(String id) {
        if(id != null && !id.equals("")) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(id);
            User result = userRepository.findById(userDTO.getUserId()).orElseThrow(IllegalArgumentException::new);
            return UserDTO.builder()
                    .userId(result.getUserId())
                    .nickname(result.getNickname())
                    .name(result.getName())
                    .phone(result.getPhone())
                    .build();
        } else {
            return null;
        }
    }

    public String updatePwd(String id, PasswordDTO passwordDTO) {
        User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        boolean isSuccess = passwordEncoder.matches(passwordDTO.getCurrentPwd(), user.getPassword());
        if (isSuccess) {
            UserDTO userDTO = UserDTO.builder()
                    .userId(user.getUserId())
                    .password(user.getPassword())
                    .build();
            userDTO.setPassword(encodingPassword(passwordDTO.getNewPwd()));
            userRepository.save(userDTO.toEntity());
            return "변경되었습니다.";
        }
        return "failed";
    }

    public String deleteUser(String id) {
        if(id != null && !id.equals("")){
            User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            if(user != null){
                userRepository.deleteById(user.getUserId());
                return "탈퇴 처리 되었습니다.";
            }
        }
        return "failed";
    }

    public List<FavorDTO> getFavorList(String id, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        if(id != null || !id.equals("")) {
            User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            List<Favor> favorList = favorRepository.findAllByUser(user, pageable);
            return favorList.stream().map(FavorDTO::new).collect(Collectors.toList());
        }
        return null;
    }

    public List<ReviewDTO> getReviewList(String id, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        if(id != null || !id.equals("")) {
            User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            List<Review> reviewList = reviewRepository.findAllByUser(user, pageable);
            return reviewList.stream().map(ReviewDTO::new).collect(Collectors.toList());
        }
        return null;
    }
}
