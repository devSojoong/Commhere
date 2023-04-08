package com.example.commhere.controller;

import com.example.commhere.dto.CommentRequestDTO;
import com.example.commhere.dto.CommentResponseDTO;
import com.example.commhere.dto.UserDTO;
import com.example.commhere.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Comment", description = "댓글 서비스를 담당합니다.")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 작성", description = "새로운 댓글을 작성 합니다.")
    @PostMapping("/board/{boardId}/comment")
    public String save(@PathVariable Long boardId, CommentRequestDTO commentRequestDTO, Authentication authentication) {
        UserDTO userDTO = (UserDTO) authentication.getPrincipal();
        return commentService.save(boardId, commentRequestDTO, userDTO.getUserId());
    }

    @Operation(summary = "댓글 수정", description = "기존 댓글을 수정합니다.")
    @PatchMapping("/comment/{commentId}")
    public String update(@PathVariable Long commentId, CommentRequestDTO commentRequestDTO, Authentication authentication){
        UserDTO userDTO = (UserDTO) authentication.getPrincipal();
        return commentService.update(commentId, commentRequestDTO, userDTO.getUserId());
    }

    @Operation(summary = "댓글 삭제", description = "기존 댓글을 삭제합니다.")
    @DeleteMapping("/comment/{commentId}")
    public String delete(@PathVariable Long commentId, CommentRequestDTO commentRequestDTO, Authentication authentication){
        UserDTO userDTO = (UserDTO) authentication.getPrincipal();
        return commentService.delete(commentId, commentRequestDTO, userDTO.getUserId());
    }

    @Operation(summary = "댓글 조회", description = "작성한 댓글을 조회합니다.")
    @GetMapping("/board/{boardId}/comment")
    public List<CommentResponseDTO> selectAll(@PathVariable Long boardId,
                                              @RequestParam(required = false, defaultValue = "1") int page){
        return commentService.selectAll(boardId, page-1);
    }

}
