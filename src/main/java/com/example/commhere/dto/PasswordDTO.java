package com.example.commhere.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PasswordDTO {
    private String currentPwd;
    private String newPwd;
}
