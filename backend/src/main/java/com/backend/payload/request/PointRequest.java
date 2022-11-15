package com.backend.payload.request;

import java.time.LocalDate;

public class PointRequest {

    private String studentNo;

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
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

    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
