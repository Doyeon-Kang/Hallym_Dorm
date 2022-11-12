package com.backend.repository.apply;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.model.apply.ApplyConsultSchedule;

public interface ApplyConsultScheduleRepository extends JpaRepository<ApplyConsultSchedule, Long> {
    @Transactional
    Optional <ApplyConsultSchedule> findByDay(String day);
}
