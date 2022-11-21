package com.backend.repository.board;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.backend.model.board.BoardNewsSubcomment;

public interface BoardNewsSubcommentRepository extends JpaRepository<BoardNewsSubcomment, Long> {
    List<BoardNewsSubcomment> findByBoardNewsCommentId(Long newsCommentId);

    @Transactional
    void deleteByBoardNewsCommentId(long newsCommentId);
}
