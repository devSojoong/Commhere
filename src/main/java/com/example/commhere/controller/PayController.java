package com.example.commhere.controller;

import com.example.commhere.dto.PayRequestDTO;
import com.example.commhere.dto.UserDTO;
import com.example.commhere.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Pay", description = "결제 서비스를 담당합니다.")
public class PayController {

    private final PayService payService;

    @Operation(summary = "상품 결제", description = "상품을 결제합니다.")
    @PostMapping("/pay")
    public String addPay(@RequestBody PayRequestDTO payRequestDTO, Authentication authentication){
        UserDTO userDTO = (UserDTO) authentication.getPrincipal();
        return payService.addPay(payRequestDTO, userDTO.getUserId());
    }

    @Operation(summary = "상품 결제 취소", description = "상품 결제를 취소합니다..")
    @PostMapping("/pay/cancel/{payId}")
    public String cancelPay(@PathVariable Long payId, Authentication authentication){
        UserDTO userDTO = (UserDTO) authentication.getPrincipal();
        return payService.cancelPay(payId, userDTO.getUserId());
    }

    @Operation(summary = "상품 구매 확정", description = "상품 구매 확정.")
    @PostMapping("/pay/confirm/{payId}")
    public String confirm(@PathVariable Long payId, Authentication authentication){
        UserDTO userDTO = (UserDTO) authentication.getPrincipal();
        return payService.confirm(payId, userDTO.getUserId());
    }

}
