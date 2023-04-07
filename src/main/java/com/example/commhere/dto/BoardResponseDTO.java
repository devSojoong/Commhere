package com.example.commhere.dto;

import com.example.commhere.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BoardResponseDTO {
    private Long boardId;
    private String title;
    private String content;
    private String nickname;
    private Integer hit;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String deletedYN;

    public BoardResponseDTO(Board board){
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.nickname = board.getUser().getNickname();
        this.hit = board.getHit();
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
        this.deletedYN = board.getDeletedYN();
    }
}
