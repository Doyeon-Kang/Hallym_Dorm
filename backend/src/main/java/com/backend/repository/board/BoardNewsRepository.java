package com.backend.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.board.BoardNews;


public interface BoardNewsRepository extends JpaRepository<BoardNews, Long>  {
    
}
