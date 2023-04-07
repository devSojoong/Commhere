package com.example.commhere.repository;

import com.example.commhere.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, String> {
    boolean existsByToken(String token);
}
