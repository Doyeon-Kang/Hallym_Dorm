package com.backend.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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

    @Column(name="writer_id")
    private String writer_id;
    public String getWriter_id() {
        return writer_id;
    }

    public void setWriter_id(String writer_id) {
        this.writer_id = writer_id;
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

    @Column(name="date")
    private Date date;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BoardNotice() {

    }

    public BoardNotice(String writer_id, String title, String contents) {
        this.writer_id = writer_id;
        this.title = title;
        this.contents = contents;
    }

}
