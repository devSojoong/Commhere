package com.example.commhere.repository;

import com.example.commhere.entity.Board;
import com.example.commhere.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBoard(Board board, Pageable pageable);
}
