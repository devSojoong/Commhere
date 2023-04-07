package com.example.commhere.repository;

import com.example.commhere.entity.Review;
import com.example.commhere.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByUser(User user, Pageable pageable);
}
