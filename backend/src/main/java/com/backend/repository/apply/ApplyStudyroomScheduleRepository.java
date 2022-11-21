package com.backend.repository.apply;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.model.apply.ApplyStudyroomSchedule;

public interface ApplyStudyroomScheduleRepository extends JpaRepository<ApplyStudyroomSchedule, Long> {
    @Transactional
    Optional <ApplyStudyroomSchedule> findBySeat(int seat);
}
