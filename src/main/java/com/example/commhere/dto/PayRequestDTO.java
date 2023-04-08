package com.example.commhere.dto;

import com.example.commhere.entity.Pay;
import com.example.commhere.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PayRequestDTO {

    private String items;
    private int amt;
    private String option;
    private String confirmation;
    private String userId;

    public PayRequestDTO(Pay pay){
        this.items = pay.getItems();
        this.amt = pay.getAmt();
        this.option = pay.getConfirmation();
        this.userId = pay.getUser().getUserId();
    }

    public Pay toEntity(User user){
        return Pay.builder()
                .items(items)
                .option(option)
                .confirmation(confirmation)
                .user(user)
                .build();
    }
}

