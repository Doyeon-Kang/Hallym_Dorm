package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.BoardNotice;


public interface BoardNoticeRepository extends JpaRepository<BoardNotice, Long>  {
    
}
