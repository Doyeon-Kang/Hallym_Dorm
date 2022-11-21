package com.backend.repository.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.board.BoardNews;


public interface BoardNewsRepository extends JpaRepository<BoardNews, Long>  {
    List<BoardNews> findByWriterStudentNo(String studentNo);      
}
