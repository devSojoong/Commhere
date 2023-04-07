package com.example.commhere.repository;

import com.example.commhere.entity.Favor;
import com.example.commhere.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavorRepository extends JpaRepository<Favor, Long> {
    List<Favor> findAllByUser(User user, Pageable pageable);
}
