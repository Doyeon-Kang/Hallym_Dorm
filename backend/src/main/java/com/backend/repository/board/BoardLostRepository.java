package com.backend.repository.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.board.BoardLost;


public interface BoardLostRepository extends JpaRepository<BoardLost, Long>  {
    List<BoardLost> findByTitleContaining(String title);
}
