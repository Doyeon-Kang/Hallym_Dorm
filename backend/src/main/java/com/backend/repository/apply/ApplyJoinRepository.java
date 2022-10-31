package com.backend.repository.apply;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.apply.ApplyJoin;

public interface ApplyJoinRepository extends JpaRepository<ApplyJoin, Long> {
    Optional<ApplyJoin> findByUserId(Long userId);
}
