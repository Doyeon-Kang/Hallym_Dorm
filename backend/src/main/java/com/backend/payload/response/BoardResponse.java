package com.backend.payload.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BoardResponse {
    private Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String writerStudentNo;

    public String getWriterStudentNo() {
        return writerStudentNo;
    }

    public void setWriterStudentNo(String writerStudentNo) {
        this.writerStudentNo = writerStudentNo;
    }

    private String writer_name;
    

    public String getWriter_name() {
        return writer_name;
    }

    public void setWriter_name(String writer_name) {
        this.writer_name = writer_name;
    }

    private String title;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String content;
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private int views;
    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime date;


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BoardResponse() {

    }

    public BoardResponse(Long id, String writerStudentNo, String writer_name, String title, String content,
                     int views, LocalDateTime date, String type) {
        this.id = id;
        this.writerStudentNo = writerStudentNo;
        this.writer_name = writer_name;
        this.title = title;
        this.content = content;
        this.views = views;
        this.date = date;
        this.type = type;
    }
}
