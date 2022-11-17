package com.backend.repository.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.board.BoardNotice;


public interface BoardNoticeRepository extends JpaRepository<BoardNotice, Long>  {
    List<BoardNotice> findByNotice1True();
    List<BoardNotice> findByNotice1False();
    List<BoardNotice> fineByWriterStudentNoAndNoticeFalse(String studentNo);
    List<BoardNotice> fineByWriterStudentNoAndNoticeTrue(String studentNo);
}
