package com.backend.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.BoardRepairComment;

public interface BoardRepairCommentRepository extends JpaRepository<BoardRepairComment, Long>  {
    List<BoardRepairComment> findByBoardRepairId(Long repairId);

    @Transactional
    void deleteByBoardRepairId(long repairId);
}
