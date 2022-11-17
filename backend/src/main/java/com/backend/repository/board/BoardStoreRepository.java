package com.backend.repository.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.board.BoardStore;


public interface BoardStoreRepository extends JpaRepository<BoardStore, Long>  {
    List<BoardStore> findByWriterStudentNo(String studentNo);      
}
