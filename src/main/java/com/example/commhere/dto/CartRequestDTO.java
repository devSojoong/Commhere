package com.example.commhere.dto;

import com.example.commhere.entity.Cart;
import com.example.commhere.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CartRequestDTO {
    private String items;
    private int amt;
    private String userId;

    public Cart toEntity(User user){
        return Cart.builder()
                .items(items)
                .amt(amt)
                .user(user)
                .build();
    }
}
