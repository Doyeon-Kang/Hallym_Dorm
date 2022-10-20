package com.backend.repository.board;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.backend.model.board.BoardStoreSubcomment;

public interface BoardStoreSubcommentRepository extends JpaRepository<BoardStoreSubcomment, Long> {
    List<BoardStoreSubcomment> findByBoardStoreCommentId(Long storeCommentId);

    @Transactional
    void deleteByBoardStoreCommentId(long storeCommentId);
}
