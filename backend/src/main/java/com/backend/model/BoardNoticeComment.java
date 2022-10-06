package com.backend.model;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

// CREATE TABLE IF NOT EXISTS `dormitory`.`board_notice_comments` (
//   `noticeId` INT NOT NULL COMMENT '게시글아이디',
//   `commentId` INT NOT NULL COMMENT '댓글아이디',
//   `comment` VARCHAR(500) NULL COMMENT '댓글내용',
//   `writerId` VARCHAR(200) NULL COMMENT '댓글 작성자 학번',
//   `writerName` VARCHAR(200) NULL COMMENT '댓글 작성자',
//   `date` DATETIME NULL COMMENT '댓글 작성일',
//   PRIMARY KEY (`noticeId`, `commentId`))
// ENGINE = InnoDB;


@Entity
@Table(name = "board_notice_comments")
public class BoardNoticeComment {
    @Id
    @NotBlank
    @Column(name="noticeId")
    private Long noticeId;

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    @Id
    @NotBlank
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="commentId")
    private Long commentId;
    
    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    @NotBlank
    @Column(name="comment")
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @NotBlank
    @Column(name="writerId")
    private String writerId;
    

    public String getWriterId() {
        return writerId;
    }

    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }

    @NotBlank
    @Column(name="writerName")
    private String writerName;

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    @Basic(optional=false)
    @Column(name="date", updatable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    @PrePersist
    private void onCreate() {   
        this.date = LocalDateTime.now();
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BoardNoticeComment() {

    }

    public BoardNoticeComment(Long noticeId, String comment, String writerId, String writerName) {
        this.noticeId = noticeId;
        this.comment = comment;
        this.writerId = writerId;
        this.writerName = writerName;
    }
}
