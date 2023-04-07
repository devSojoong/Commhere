package com.example.commhere.dto;

import com.example.commhere.entity.Board;
import com.example.commhere.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDTO {
    private String title;
    private String content;
    private String userId;

    public BoardRequestDTO(Board board){
        this.title = board.getTitle();
        this.content = board.getContent();
        this.userId = board.getUser().getUserId();
    }

    public Board toEntity(User user){
        return Board.builder()
                .title(title)
                .content(content)
                .user(user)
                .hit(0)
                .build();
    }
}
