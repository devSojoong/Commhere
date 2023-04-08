package com.example.commhere.dto;

import com.example.commhere.entity.Comment;
import com.example.commhere.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CommentRequestDTO {
    private String content;
    private String userId;
    private Long boardId;

    public CommentRequestDTO(Comment comment){
        this.content = comment.getContent();
        this.userId = comment.getUser().getUserId();
        this.boardId = getBoardId();
    }

    public Comment toEntity(User user){
        return Comment.builder()
                .content(content)
                .user(user)
                .build();
    }
}
