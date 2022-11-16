package com.backend.repository.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.board.BoardNotice;


public interface BoardNoticeRepository extends JpaRepository<BoardNotice, Long>  {
    List<BoardNotice> findByNotice1True();
    List<BoardNotice> findByNotice1False();
    List<BoardNotice> findByWriterStudentNoAndNotice1False(String studentNo);
    List<BoardNotice> findByWriterStudentNoAndNotice1True(String studentNo);
}
