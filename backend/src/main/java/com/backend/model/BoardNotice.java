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

@Entity
@Table(name = "board_notice")
public class BoardNotice {
//     `id` INT NOT NULL AUTO_INCREMENT COMMENT '게시글  아이디',
//   `writer_id` VARCHAR(200) NOT NULL COMMENT '작성자 아이디',
//   `title` VARCHAR(200) NULL COMMENT '제목',
//   `contents` VARCHAR(1000) NULL COMMENT '내용',
//   `views` INT NULL COMMENT '조회수',
//   `date` DATE NULL COMMENT '작성일자',
//   `user_member_id` VARCHAR(200) NULL,
//   `user_member_no` INT NULL,
    @Id
    @NotBlank
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="writer_username")
    private String writer_username;

    public String getWriter_username() {
        return writer_username;
    }

    public void setWriter_username(String writer_username) {
        this.writer_username = writer_username;
    }

    @Column(name="writer_name")
    private String writer_name;
    

    public String getWriter_name() {
        return writer_name;
    }

    public void setWriter_name(String writer_name) {
        this.writer_name = writer_name;
    }

    @Column(name="title")
    private String title;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name="contents")
    private String contents;
    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Column(name="views")
    private int views;
    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
    @Basic(optional=false)
    @Column(name="date", updatable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime date;

    @PrePersist
    private void onCreate() {   
        this.date = LocalDateTime.now();
    }
    // @PrePersist
    // private void onCreate() {   
    //     this.date = new Date();
    // }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BoardNotice() {

    }

    public BoardNotice(String writer_username, String writer_name, String title, String contents) {
        this.writer_username = writer_username;
        this.writer_name = writer_name;
        this.title = title;
        this.contents = contents;
    }

}
