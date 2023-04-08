package com.example.commhere.controller;

import com.example.commhere.dto.CartRequestDTO;
import com.example.commhere.dto.UserDTO;
import com.example.commhere.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Cart", description = "장바구니 서비스를 담당합니다.")
public class CartController {
    private final CartService cartService;

    @Operation(summary = "장바구니 추가", description = "상품을 장바구니에 담습니다.")
    @PostMapping("/cart")
    public String addCart(@RequestBody CartRequestDTO cartRequestDTO, Authentication authentication){
        UserDTO userDTO = (UserDTO) authentication.getPrincipal();
        return cartService.addCart(cartRequestDTO, userDTO.getUserId());
    }

    @Operation(summary = "장바구니 삭제", description = "장바구니에 담긴 상품을 삭제하였습니다.")
    @DeleteMapping("/cart/{cartId}")
    public String deleteCart(@PathVariable Long cartId){
        return cartService.deleteCart(cartId);
    }
}
