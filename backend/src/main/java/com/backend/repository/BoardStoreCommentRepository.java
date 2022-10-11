package com.backend.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.BoardStoreComment;

public interface BoardStoreCommentRepository extends JpaRepository<BoardStoreComment, Long>  {
    List<BoardStoreComment> findByBoardStoreId(Long storeId);

    @Transactional
    void deleteByBoardStoreId(long storeId);
}
