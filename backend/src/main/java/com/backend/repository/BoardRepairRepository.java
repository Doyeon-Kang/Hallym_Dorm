package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.BoardRepair;


public interface BoardRepairRepository extends JpaRepository<BoardRepair, Long>  {
    
}
