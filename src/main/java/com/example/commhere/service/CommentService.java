package com.example.commhere.service;

import com.example.commhere.dto.CommentRequestDTO;
import com.example.commhere.dto.CommentResponseDTO;
import com.example.commhere.entity.Board;
import com.example.commhere.entity.Comment;
import com.example.commhere.entity.User;
import com.example.commhere.repository.BoardRepository;
import com.example.commhere.repository.CommentRepository;
import com.example.commhere.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public String save(Long boardId, CommentRequestDTO commentRequestDTO, String userId) {
        if (boardId != null && userId != null && !userId.equals("")) {
            Board board = boardRepository.findById(boardId).orElseThrow(() ->
                    new IllegalArgumentException("댓글 작성 실패 : 해당 게시글 id가 존재하지 않습니다. => " + boardId));
            User user = userRepository.findById(userId).orElseThrow(() ->
                    new IllegalArgumentException("댓글 작성 실패 : 해당 계정이 존재하지 않습니다. => " + userId));
            if(commentRequestDTO == null || commentRequestDTO.getContent().equals("")){
                return "내용을 입력해주세요.";
            }
            commentRequestDTO.setBoardId(boardId);
            commentRequestDTO.setUserId(userId);

            commentRepository.save(commentRequestDTO.toEntity(user));
            return "댓글을 작성하였습니다.";
        }
        return "댓글 작성이 실패하였습니다.";
    }

    public String update(Long commentId, CommentRequestDTO commentRequestDTO, String userId) {
        if(commentId != null){
            if(commentRequestDTO != null && !commentRequestDTO.getContent().equals("")){
                User user = userRepository.findById(userId).orElseThrow(() ->
                        new IllegalArgumentException("댓글 수정 실패 : 해당 계정이 존재하지 않습니다. => " + userId));
                if(!userId.equals(user.getUserId())) return "댓글 작성자만 댓글 수정이 가능합니다.";
                commentRepository.save(Comment.builder()
                                .commentId(commentId)
                                .content(commentRequestDTO.getContent())
                                .build());
                return "댓글을 수정하였습니다.";
            }
        }
        return "댓글 수정을 실패하였습니다.";
    }

    public String delete(Long commentId, CommentRequestDTO commentRequestDTO, String userId) {
        if(commentId != null){
            if(commentRequestDTO != null && !commentRequestDTO.getContent().equals("")){
                User user = userRepository.findById(userId).orElseThrow(() ->
                        new IllegalArgumentException("댓글 삭제 실패 : 해당 계정이 존재하지 않습니다. => " + userId));
                if(!userId.equals(user.getUserId())) return "댓글 작성자만 댓글 삭제가 가능합니다.";
                commentRepository.deleteById(commentId);
                return "댓글을 삭제하였습니다.";
            }
        }
        return "댓글 삭제에 실패하였습니다.";
    }

    public List<CommentResponseDTO> selectAll(Long boardId, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        if(boardId != null) {
            Board board = boardRepository.findById(boardId).orElseThrow(()->
                    new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
            List<Comment> commentList = commentRepository.findAllByBoard(board, pageable);
            return commentList.stream().map(CommentResponseDTO::new).collect(Collectors.toList());
        }
        return null;
    }
}
