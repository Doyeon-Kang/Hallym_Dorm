package com.backend.payload.request;

import java.time.LocalDate;

public class ApplySleepoutRequest {
    // public ApplySleepout(LocalDate date_sleepout, String reason) {
    private String studentNo;

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    private LocalDate date_sleepout;

    public LocalDate getDate_sleepout() {
        return date_sleepout;
    }

    public void setDate_sleepout(LocalDate date_sleepout) {
        this.date_sleepout = date_sleepout;
    }

    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
