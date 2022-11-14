package com.backend.repository.apply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.apply.ApplyConsult;

public interface ApplyConsultRepository extends JpaRepository<ApplyConsult, Long>  {
    List<ApplyConsult> findByUserMemberId(Long userMemberId);
}
