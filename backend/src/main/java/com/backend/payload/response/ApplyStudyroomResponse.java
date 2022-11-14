package com.backend.payload.response;

import java.time.LocalDateTime;

public class ApplyStudyroomResponse {
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

    private LocalDateTime date;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    private int seat;

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    private int timeslot1;

    public int getTimeslot1() {
        return timeslot1;
    }

    public void setTimeslot1(int timeslot1) {
        this.timeslot1 = timeslot1;
    }

    private int timeslot2;

    public int getTimeslot2() {
        return timeslot2;
    }

    public void setTimeslot2(int timeslot2) {
        this.timeslot2 = timeslot2;
    }

    private int timeslot3;

    public int getTimeslot3() {
        return timeslot3;
    }

    public void setTimeslot3(int timeslot3) {
        this.timeslot3 = timeslot3;
    }

    public ApplyStudyroomResponse() {

    }

    public ApplyStudyroomResponse(Long id, String studentNo, String name,
                                String department, int seat, int timeslot1, 
                                int timeslot2, int timeslot3, LocalDateTime date) {
                                    this.id = id;
                                    this.studentNo = studentNo;
                                    this.name = name;
                                    this.department = department;
                                    this.date = date;
                                    this.seat = seat;
                                    this.timeslot1 = timeslot1;
                                    this.timeslot2 = timeslot2;
                                    this.timeslot3 = timeslot3;
                                }
}
