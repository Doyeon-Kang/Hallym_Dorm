package com.backend.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.BoardNoticeComment;

public interface BoardNoticeCommentRepository extends JpaRepository<BoardNoticeComment, Long>  {
    List<BoardNoticeComment> findByBoardNoticeId(Long noticeId);

    @Transactional
    void deleteByBoardNoticeId(long noticeId);
}
