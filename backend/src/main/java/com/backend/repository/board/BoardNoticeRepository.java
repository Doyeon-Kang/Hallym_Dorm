package com.backend.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.board.BoardNotice;


public interface BoardNoticeRepository extends JpaRepository<BoardNotice, Long>  {
    
}
