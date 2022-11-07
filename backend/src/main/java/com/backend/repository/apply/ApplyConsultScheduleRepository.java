package com.backend.repository.apply;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.apply.ApplyConsultSchedule;

public interface ApplyConsultScheduleRepository extends JpaRepository<ApplyConsultSchedule, Long> {
    Optional<ApplyConsultSchedule> findByUserId(Long userId);
}
