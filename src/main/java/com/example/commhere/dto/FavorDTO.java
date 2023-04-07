package com.example.commhere.dto;

import com.example.commhere.entity.Favor;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FavorDTO {
    private Long id;
    private String storeNm;
    private String nickname;

    public FavorDTO(Favor favor) {
        this.id = favor.getFavorId();
        this.storeNm = favor.getStore().getStoreNm();
        this.nickname = favor.getUser().getNickname();
    }
}
