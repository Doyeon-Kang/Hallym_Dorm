package com.backend.model.board;

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
@Table(name = "board_store")
public class BoardStore {

    @Id
    @NotBlank
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_store_generator")
    private Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="writer_studentno")
    private String writerStudentNo;

    public String getWriterStudentNo() {
        return writerStudentNo;
    }

    public void setWriterStudentNo(String writerStudentNo) {
        this.writerStudentNo = writerStudentNo;
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

    @Column(name="content")
    private String content;
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BoardStore() {

    }

    public BoardStore(String writerStudentNo, String writer_name, String title, String content) {
        this.writerStudentNo = writerStudentNo;
        this.writer_name = writer_name;
        this.title = title;
        this.content = content;
    }
}
