package com.backend.repository.apply;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.apply.ApplyStudyroomSchedule;

public interface ApplyStudyroomScheduleRepository extends JpaRepository<ApplyStudyroomSchedule, Long> {
    Optional<ApplyStudyroomSchedule> findByUserId(Long userId);
}
