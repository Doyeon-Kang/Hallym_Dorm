package com.backend.repository.apply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.apply.ApplyStudyroom;

public interface ApplyStudyroomRepository extends JpaRepository<ApplyStudyroom, Long>  {
    List<ApplyStudyroom> findByUserMemberId(Long userMemberId);
}
