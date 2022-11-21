package com.backend.model.apply;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.backend.model.UserMember;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "apply_consult")
public class ApplyConsult {
//   `id` INT NOT NULL AUTO_INCREMENT COMMENT '아이디',
    @Id
    @NotBlank
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apply_consult_generator")
    @Column(name="id")
    private Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="day_of_week")
    private String day_of_week;

    public String getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(String day_of_week) {
        this.day_of_week = day_of_week;
    }

    //   `timeslot` INT NOT NULL COMMENT '',
    @Column(name="timeslot1_okay")
    private boolean timeslot1_okay;

    public boolean isTimeslot1_okay() {
        return timeslot1_okay;
    }

    public void setTimeslot1_okay(boolean timeslot1_okay) {
        this.timeslot1_okay = timeslot1_okay;
    }

    @Column(name="timeslot2_okay")
    private boolean timeslot2_okay;

    public boolean isTimeslot2_okay() {
        return timeslot2_okay;
    }

    public void setTimeslot2_okay(boolean timeslot2_okay) {
        this.timeslot2_okay = timeslot2_okay;
    }

    @Column(name="timeslot3_okay")
    private boolean timeslot3_okay;

    public boolean isTimeslot3_okay() {
        return timeslot3_okay;
    }

    public void setTimeslot3_okay(boolean timeslot3_okay) {
        this.timeslot3_okay = timeslot3_okay;
    }

    @Column(name="timeslot4_okay")
    private boolean timeslot4_okay;

    public boolean isTimeslot4_okay() {
        return timeslot4_okay;
    }

    public void setTimeslot4_okay(boolean timeslot4_okay) {
        this.timeslot4_okay = timeslot4_okay;
    }

    @Column(name="timeslot5_okay")
    private boolean timeslot5_okay;

    public boolean isTimeslot5_okay() {
        return timeslot5_okay;
    }

    public void setTimeslot5_okay(boolean timeslot5_okay) {
        this.timeslot5_okay = timeslot5_okay;
    }

    @Column(name="timeslot6_okay")
    private boolean timeslot6_okay;

    public boolean isTimeslot6_okay() {
        return timeslot6_okay;
    }

    public void setTimeslot6_okay(boolean timeslot6_okay) {
        this.timeslot6_okay = timeslot6_okay;
    }

    @Column(name="timeslot7_okay")
    private boolean timeslot7_okay;

    public boolean isTimeslot7_okay() {
        return timeslot7_okay;
    }

    public void setTimeslot7_okay(boolean timeslot7_okay) {
        this.timeslot7_okay = timeslot7_okay;
    }

    @Column(name="timeslot8_okay")
    private boolean timeslot8_okay;

    public boolean isTimeslot8_okay() {
        return timeslot8_okay;
    }

    public void setTimeslot8_okay(boolean timeslot8_okay) {
        this.timeslot8_okay = timeslot8_okay;
    }

    // `topic` VARCHAR(20) NULL COMMENT '',
    @Column(name="topic")
    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    // `subject` VARCHAR(100) NULL COMMENT '',
    @Column(name="subject")
    private String subject;
    

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    //   `date` DATETIME COMMENT '',
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userMember", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    UserMember userMember;
    
    public UserMember getUserMember() {
        return userMember;
    }

    public void setUserMember(UserMember userMember) {
        this.userMember = userMember;
    }


    public ApplyConsult() {

    }

    public ApplyConsult(String topic, String subject) {
        this.topic = topic;
        this.subject = subject;
    }
    
}
