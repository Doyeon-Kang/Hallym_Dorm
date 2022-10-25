package com.backend.repository.board;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.backend.model.board.BoardLostSubcomment;

public interface BoardLostSubcommentRepository extends JpaRepository<BoardLostSubcomment, Long> {
    List<BoardLostSubcomment> findByBoardLostCommentId(Long lostCommentId);

    @Transactional
    void deleteByBoardLostCommentId(long lostCommentId);
}
