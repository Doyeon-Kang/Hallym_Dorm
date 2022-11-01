package com.backend.repository.board;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.board.BoardNewsComment;

public interface BoardNewsCommentRepository extends JpaRepository<BoardNewsComment, Long>  {
    List<BoardNewsComment> findByBoardNewsId(Long newsId);

    @Transactional
    void deleteByBoardNewsId(long newsId);
}
