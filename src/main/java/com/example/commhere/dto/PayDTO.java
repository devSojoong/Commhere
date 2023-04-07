package com.example.commhere.dto;

import com.example.commhere.entity.Pay;
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
    private String nickname;
    private LocalDateTime createdDate;

    public PayDTO(Pay pay){
        this.payId = pay.getPayId();
        this.items = pay.getItems();
        this.amt = pay.getAmt();
        this.option = pay.getOption();
        this.confirmation = pay.getConfirmation();
        this.nickname = pay.getUser().getNickname();
        this.createdDate = pay.getCreatedDate();
    }
}
