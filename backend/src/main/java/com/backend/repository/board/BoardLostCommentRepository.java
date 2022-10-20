package com.backend.repository.board;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.board.BoardLostComment;

public interface BoardLostCommentRepository extends JpaRepository<BoardLostComment, Long>  {
    List<BoardLostComment> findByBoardLostId(Long lostId);

    @Transactional
    void deleteByBoardLostId(long lostId);
}
