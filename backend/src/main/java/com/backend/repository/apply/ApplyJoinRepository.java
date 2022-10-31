package com.backend.repository.apply;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.apply.ApplyJoin;

public interface ApplyJoinRepository extends JpaRepository<ApplyJoin, Long> {
    ApplyJoin findByUserId(Long userId);
}
