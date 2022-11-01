package com.backend.repository.apply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.apply.ApplySleepout;

public interface ApplySleepoutRepository extends JpaRepository<ApplySleepout, Long>  {
    List<ApplySleepout> findByUserMemberId(Long userMemberId);
}
