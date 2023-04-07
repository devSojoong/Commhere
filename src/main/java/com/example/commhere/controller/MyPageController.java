package com.example.commhere.controller;

import com.example.commhere.dto.*;
import com.example.commhere.repository.TokenRepository;
import com.example.commhere.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Auth", description = "인증 기능 API")
public class MyPageController {

    private final UserService userService;
    private final TokenRepository tokenRepository;

    @Operation(summary = "마이페이지내 회원 정보", description = "회원의 정보를 보여줍니다.")
    @GetMapping("/mypage/info/{id}")
    public UserDTO myPageInfo(@PathVariable String id){
        return userService.getUserInfo(id);
    }

    @Operation(summary = "비밀번호 변경", description = "패스워드를 확인 후 변경합니다.")
    @PostMapping("/mypage/changePassword/{id}")
    public String changePassword(@PathVariable String id,
                                 @RequestBody PasswordDTO passwordDTO){
        if(passwordDTO.getCurrentPwd().equals("") || passwordDTO.getNewPwd().equals("")) return "비밀번호를 다시 입력해주세요.";
        return userService.updatePwd(id, passwordDTO);
    }

    @Operation(summary = "회원 탈퇴", description = "회원을 탈퇴합니다.")
    @DeleteMapping("/mypage/leave/{id}")
    public String leaveAccount(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    @Operation(summary = "찜 목록 조회", description = "회원의 찜 목록을 조회합니다.")
    @GetMapping("/mypage/favor/{id}")
    public List<FavorDTO> favorList(@PathVariable String id,
                                    @RequestParam(required = false, defaultValue = "1") int page) {
        return userService.getFavorList(id, page-1);
    }

    @Operation(summary = "리뷰 목록 조회", description = "회원의 리뷰 목록을 조회합니다.")
    @GetMapping("/mypage/review/{id}")
    public List<ReviewDTO> reviewList(@PathVariable String id,
                                      @RequestParam(required = false, defaultValue = "1") int page){
        return userService.getReviewList(id, page-1);
    }

    @Operation(summary = "결제 목록 조회", description = "회원의 결제 목록을 조회합니다.")
    @GetMapping("/payment/history/{id}")
    public List<PayDTO> payList(@PathVariable String id,
                                @RequestParam(required = false, defaultValue = "1") int page){
        return userService.getPayList(id, page-1);
    }

    @Operation(summary = "장바구니 목록 조회", description = "회원의 장바구니 목록을 조회합니다.")
    @GetMapping("/cart/{id}")
    public List<CartDTO> cartList(@PathVariable String id,
                                  @RequestParam(required = false, defaultValue = "1") int page) {
        return userService.getCartList(id, page-1);
    }

}
