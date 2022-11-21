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
@Table(name = "apply_studyroom")
public class ApplyStudyroom {
//   `id` INT NOT NULL AUTO_INCREMENT COMMENT '아이디',
    @Id
    @NotBlank
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apply_studyroom_generator")
    @Column(name="id")
    private Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Column(name="seat")
    private int seat;

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    //   `timeslot` INT NOT NULL COMMENT '',
    @Column(name="timeslot1")
    private int timeslot1;

    public int getTimeslot1() {
        return timeslot1;
    }

    public void setTimeslot1(int timeslot1) {
        this.timeslot1 = timeslot1;
    }

    @Column(name="timeslot2")
    private int timeslot2;

    public int getTimeslot2() {
        return timeslot2;
    }

    public void setTimeslot2(int timeslot2) {
        this.timeslot2 = timeslot2;
    }

    @Column(name="timeslot3")
    private int timeslot3;

    public int getTimeslot3() {
        return timeslot3;
    }

    public void setTimeslot3(int timeslot3) {
        this.timeslot3 = timeslot3;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "applyStudyroomSchedule", nullable = false)
    // @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    ApplyStudyroomSchedule applyStudyroomSchedule;

    public ApplyStudyroomSchedule getApplyStudyroomSchedule() {
        return applyStudyroomSchedule;
    }

    public void setApplyStudyroomSchedule(ApplyStudyroomSchedule applyStudyroomSchedule) {
        this.applyStudyroomSchedule = applyStudyroomSchedule;
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


    public ApplyStudyroom() {

    }

    public ApplyStudyroom(int seat, int timeslot1, int timeslot2, int timeslot3) {
        this.seat = seat;
        this.timeslot1 = timeslot1;
        this.timeslot2 = timeslot2;
        this.timeslot3 = timeslot3;
    }
    
}
