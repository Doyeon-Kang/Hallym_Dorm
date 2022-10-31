package com.backend.repository.apply;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.apply.ApplyResign;

public interface ApplyResignRepository extends JpaRepository<ApplyResign, Long>  {
    Optional<ApplyResign> findByUserId(Long userId);
}
