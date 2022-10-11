package com.backend.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.BoardLostComment;

public interface BoardLostCommentRepository extends JpaRepository<BoardLostComment, Long>  {
    List<BoardLostComment> findByBoardLostId(Long lostId);

    @Transactional
    void deleteByBoardLostId(long lostId);
}
