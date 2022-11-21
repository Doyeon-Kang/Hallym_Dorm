package com.backend.payload.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApplySleepoutResponse {
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

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime date;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    private Boolean approved;

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public ApplySleepoutResponse() {

    }

    public ApplySleepoutResponse(Long id, String studentNo, String name, String department,
                                LocalDate date_sleepout, String reason, LocalDateTime date, Boolean approved) {
        this.id = id;
        this.studentNo = studentNo;
        this.name = name;
        this.department = department;
        this.date_sleepout = date_sleepout;
        this.reason = reason;
        this.date = date;
        this.approved = approved;
    }
}
