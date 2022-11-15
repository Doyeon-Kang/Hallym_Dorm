package com.backend.payload.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PointResponse {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String studentNo;

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    private LocalDate date_receive;

    public LocalDate getDate_receive() {
        return date_receive;
    }

    public void setDate_receive(LocalDate date_receive) {
        this.date_receive = date_receive;
    }

    private LocalDateTime date;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public PointResponse() {

    }

    public PointResponse(Long id, String studentNo, String name, String department,
                                String reason, LocalDate date_receive, LocalDateTime date, int score) {
        this.id = id;
        this.studentNo = studentNo;
        this.name = name;
        this.department = department;
        this.reason = reason;
        this.date_receive = date_receive;
        this.date = date;
        this.score = score;
    }
}
