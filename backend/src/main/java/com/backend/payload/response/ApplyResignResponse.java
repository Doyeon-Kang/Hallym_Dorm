package com.backend.payload.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApplyResignResponse {
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

    private LocalDate res_date;

    public LocalDate getRes_date() {
        return res_date;
    }

    public void setRes_date(LocalDate res_date) {
        this.res_date = res_date;
    }

    private String res_reason;

    public String getRes_reason() {
        return res_reason;
    }

    public void setRes_reason(String res_reason) {
        this.res_reason = res_reason;
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

    public ApplyResignResponse(Long id, String studentNo, String name, String department,
                                LocalDate res_date, String res_reason, LocalDateTime date, Boolean approved) {
        this.id = id;
        this.studentNo = studentNo;
        this.name = name;
        this.department = department;
        this.res_date = res_date;
        this.res_reason = res_reason;
        this.date = date;
        this.approved = approved;
    }
}

