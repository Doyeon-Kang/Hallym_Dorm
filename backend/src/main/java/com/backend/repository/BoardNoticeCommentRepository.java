package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.BoardNoticeComment;

public interface BoardNoticeCommentRepository extends JpaRepository<BoardNoticeComment, Long>  {
    List<BoardNoticeComment> findAllByNoticeId(Long noticeId);
    BoardNoticeComment findByNoticeIdAndCommentId(Long noticeId, Long commentId);
    List<BoardNoticeComment> deleteByNoticeId(Long noticeId);
    BoardNoticeComment deletebyNoticeIdAndCommentId(Long noticeId, Long commentId);
}
