package com.example.commhere.service;

import com.example.commhere.dto.CartRequestDTO;
import com.example.commhere.entity.User;
import com.example.commhere.repository.CartRepository;
import com.example.commhere.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    public String addCart(CartRequestDTO cartRequestDTO, String userId) {
        if(cartRequestDTO != null && !cartRequestDTO.getItems().equals("") && cartRequestDTO.getAmt() != 0){
            User user = userRepository.findById(userId).orElseThrow(()->
                    new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
            cartRepository.save(cartRequestDTO.toEntity(user));
            return "장바구니에 추가되었습니다.";
        }
        return "장바구니에 담지 못하였습니다.";
    }

    public String deleteCart(Long cartId) {
        if(cartId != null){
            cartRepository.deleteById(cartId);
            return "장바구니 삭제가 완료되었습니다.";
        }
        return "장바구니 삭제를 실패하였습니다. 다시 한번 확인해주세요.";
    }
}
