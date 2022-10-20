package com.backend.repository.board;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.backend.model.board.BoardNoticeSubcomment;

public interface BoardNoticeSubcommentRepository extends JpaRepository<BoardNoticeSubcomment, Long> {
    List<BoardNoticeSubcomment> findByBoardNoticeCommentId(Long noticeCommentId);

    @Transactional
    void deleteByBoardNoticeCommentId(long noticeCommentId);
}
