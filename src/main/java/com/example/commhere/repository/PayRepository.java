package com.example.commhere.repository;

import com.example.commhere.entity.Pay;
import com.example.commhere.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayRepository extends JpaRepository<Pay, Long> {
    List<Pay> findAllByUser(User user, Pageable pageable);
}
