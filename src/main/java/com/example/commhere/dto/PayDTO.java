package com.example.commhere.dto;

import com.example.commhere.entity.Pay;
import com.example.commhere.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PayDTO {
    private Long payId;
    private String items;
    private int amt;
    private String option;
    private String confirmation;
    private String userId;
    private LocalDateTime createdDate;
    private String deletedYN;

    public PayDTO(Pay pay){
        this.payId = pay.getPayId();
        this.items = pay.getItems();
        this.amt = pay.getAmt();
        this.option = pay.getOption();
        this.confirmation = pay.getConfirmation();
        this.userId = pay.getUser().getUserId();
        this.createdDate = pay.getCreatedDate();
        this.deletedYN = pay.getDeletedYN();
    }

    public Pay toEntity(User user){
        return Pay.builder()
                .payId(payId)
                .items(items)
                .amt(amt)
                .option(option)
                .confirmation(confirmation)
                .user(user)
                .createdDate(createdDate)
                .deletedYN(deletedYN)
                .build();
    }
}
