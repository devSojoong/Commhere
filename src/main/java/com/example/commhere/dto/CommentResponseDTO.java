package com.example.commhere.dto;

import com.example.commhere.entity.Comment;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CommentResponseDTO {
    private Long commentId;
    private String content;
    private String userId;
    private Long boardId;

    public CommentResponseDTO (Comment comment) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.userId = comment.getUser().getUserId();
        this.boardId = comment.getBoard().getBoardId();
    }
}
