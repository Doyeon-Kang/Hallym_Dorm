package com.backend.repository.board;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.board.BoardRepairComment;

public interface BoardRepairCommentRepository extends JpaRepository<BoardRepairComment, Long>  {
    Optional<BoardRepairComment> findByBoardRepairId(Long repairId);

    @Transactional
    void deleteByBoardRepairId(long repairId);
}
