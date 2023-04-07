package com.example.commhere.repository;

import com.example.commhere.entity.Cart;
import com.example.commhere.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUser(User user, Pageable pageable);
}
