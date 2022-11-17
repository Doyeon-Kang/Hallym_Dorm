package com.backend.repository.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.board.BoardRepair;


public interface BoardRepairRepository extends JpaRepository<BoardRepair, Long>  {
    List<BoardRepair> findByWriterStudentNo(String studentNo);    
}
