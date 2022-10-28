package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.ApplySleepout;


public interface ApplySleepoutRepository extends JpaRepository<ApplySleepout, Long>  {
    List<ApplySleepout> findByApprovedTrue();
    List<ApplySleepout> findByApprovedFalse();

}
