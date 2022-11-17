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

    private int plusPoint;

    public int getPlusPoint() {
        return plusPoint;
    }

    public void setPlusPoint(int plusPoint) {
        this.plusPoint = plusPoint;
    }

    private int minusPoint;

    public int getMinusPoint() {
        return minusPoint;
    }

    public void setMinusPoint(int minusPoint) {
        this.minusPoint = minusPoint;
    }

    public PointResponse() {

    }

    public PointResponse(Long id, String studentNo, String name, String department,
                                String reason, LocalDate date_receive, LocalDateTime date, int plusPoint, int minusPoint) {
        this.id = id;
        this.studentNo = studentNo;
        this.name = name;
        this.department = department;
        this.reason = reason;
        this.date_receive = date_receive;
        this.date = date;
        this.plusPoint = plusPoint;
        this.minusPoint = minusPoint;
    }
}
