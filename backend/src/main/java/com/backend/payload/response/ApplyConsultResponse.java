package com.backend.payload.response;

import java.time.LocalDateTime;

public class ApplyConsultResponse {
    // `id` INT NOT NULL AUTO_INCREMENT COMMENT '아이디',
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

    // `day_of_week` VARCHAR(3) COMMENT '',
    private String day_of_week;

    public String getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(String day_of_week) {
        this.day_of_week = day_of_week;
    }

    // `timeslot1_okay` BOOLEAN NOT NULL DEFAULT 0 COMMENT '',
    private boolean timeslot1_okay;

    public boolean isTimeslot1_okay() {
        return timeslot1_okay;
    }

    public void setTimeslot1_okay(boolean timeslot1_okay) {
        this.timeslot1_okay = timeslot1_okay;
    }

    // `timeslot2_okay` BOOLEAN NOT NULL DEFAULT 0 COMMENT '',
    private boolean timeslot2_okay;

    public boolean isTimeslot2_okay() {
        return timeslot2_okay;
    }

    public void setTimeslot2_okay(boolean timeslot2_okay) {
        this.timeslot2_okay = timeslot2_okay;
    }

    // `timeslot3_okay` BOOLEAN NOT NULL DEFAULT 0 COMMENT '',
    private boolean timeslot3_okay;

    public boolean isTimeslot3_okay() {
        return timeslot3_okay;
    }

    public void setTimeslot3_okay(boolean timeslot3_okay) {
        this.timeslot3_okay = timeslot3_okay;
    }

    // `timeslot4_okay` BOOLEAN NOT NULL DEFAULT 0 COMMENT '',
    private boolean timeslot4_okay;

    public boolean isTimeslot4_okay() {
        return timeslot4_okay;
    }

    public void setTimeslot4_okay(boolean timeslot4_okay) {
        this.timeslot4_okay = timeslot4_okay;
    }

    // `timeslot5_okay` BOOLEAN NOT NULL DEFAULT 0 COMMENT '',
    private boolean timeslot5_okay;

    public boolean isTimeslot5_okay() {
        return timeslot5_okay;
    }

    public void setTimeslot5_okay(boolean timeslot5_okay) {
        this.timeslot5_okay = timeslot5_okay;
    }

    // `timeslot6_okay` BOOLEAN NOT NULL DEFAULT 0 COMMENT '',
    private boolean timeslot6_okay;

    public boolean isTimeslot6_okay() {
        return timeslot6_okay;
    }

    public void setTimeslot6_okay(boolean timeslot6_okay) {
        this.timeslot6_okay = timeslot6_okay;
    }

    // `timeslot7_okay` BOOLEAN NOT NULL DEFAULT 0 COMMENT '',
    private boolean timeslot7_okay;

    public boolean isTimeslot7_okay() {
        return timeslot7_okay;
    }

    public void setTimeslot7_okay(boolean timeslot7_okay) {
        this.timeslot7_okay = timeslot7_okay;
    }

    // `timeslot8_okay` BOOLEAN NOT NULL DEFAULT 0 COMMENT '',
    private boolean timeslot8_okay;

    public boolean isTimeslot8_okay() {
        return timeslot8_okay;
    }

    public void setTimeslot8_okay(boolean timeslot8_okay) {
        this.timeslot8_okay = timeslot8_okay;
    } 

    // `topic` VARCHAR(20) NULL COMMENT '',
    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    // `subject` VARCHAR(100) NULL COMMENT '',
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    // `date` DATETIME COMMENT '',
    private LocalDateTime date;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ApplyConsultResponse() {

    }

    public ApplyConsultResponse(Long id, String studentNo, String name, String department,
                                String day_of_week, boolean timeslot1_okay, boolean timeslot2_okay, boolean timeslot3_okay,
                                boolean timeslot4_okay, boolean timeslot5_okay, boolean timeslot6_okay, boolean timeslot7_okay,
                                boolean timeslot8_okay, String topic, String subject, LocalDateTime date) {
                                    this. id = id;
                                    this.studentNo = studentNo;
                                    this.name = name;
                                    this.department = department;
                                    this.day_of_week = day_of_week;
                                    this.timeslot1_okay = timeslot1_okay;
                                    this.timeslot2_okay = timeslot2_okay;
                                    this.timeslot3_okay = timeslot3_okay;
                                    this.timeslot4_okay = timeslot4_okay;
                                    this.timeslot5_okay = timeslot5_okay;
                                    this.timeslot6_okay = timeslot6_okay;
                                    this.timeslot7_okay = timeslot7_okay;
                                    this.timeslot8_okay = timeslot8_okay;
                                    this.topic = topic;
                                    this.subject = subject;
                                    this.date = date;
                                }
}
