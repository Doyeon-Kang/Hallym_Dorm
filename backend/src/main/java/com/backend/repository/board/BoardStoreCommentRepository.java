package com.backend.repository.board;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.board.BoardStoreComment;

public interface BoardStoreCommentRepository extends JpaRepository<BoardStoreComment, Long>  {
    List<BoardStoreComment> findByBoardStoreId(Long storeId);

    @Transactional
    void deleteByBoardStoreId(long storeId);
}
