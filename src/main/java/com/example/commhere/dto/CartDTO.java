package com.example.commhere.dto;

import com.example.commhere.entity.Cart;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CartDTO {
    private Long cartId;
    private String items;
    private int amt;
    private String nickname;

    public CartDTO(Cart cart){
        this.cartId = cart.getCartId();
        this.items = cart.getItems();
        this.amt = cart.getAmt();
        this.nickname = cart.getUser().getNickname();
    }
}
