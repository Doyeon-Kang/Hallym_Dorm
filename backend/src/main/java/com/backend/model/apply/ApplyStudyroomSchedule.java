package com.backend.model.apply;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.backend.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "studyroom_schedule")
public class ApplyStudyroomSchedule {
    // `id` INT NOT NULL AUTO_INCREMENT COMMENT '아이디',
    @Id
    @NotBlank
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consult_schedule_generator")
    @Column(name="id")
    private Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//  `seat` INT NOT NULL COMMENT '',
    @Column(name="seat")
    private int seat;
    
    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    // `timeslot1_taken` BOOLEAN NOT NULL DEFAULT 0 COMMENT '',
    @Column(name="timeslot1_taken")
    private boolean timeslot1_taken = false;
    public boolean isTimeslot1_taken() {
        return timeslot1_taken;
    }

    public void setTimeslot1_taken(boolean timeslot1_taken) {
        this.timeslot1_taken = timeslot1_taken;
    }

    // `timeslot2_taken` BOOLEAN NOT NULL DEFAULT 0 COMMENT '',
    @Column(name="timeslot2_taken")
    private boolean timeslot2_taken = false;
    public boolean isTimeslot2_taken() {
        return timeslot2_taken;
    }

    public void setTimeslot2_taken(boolean timeslot2_taken) {
        this.timeslot2_taken = timeslot2_taken;
    }
    
    // `timeslot3_taken` BOOLEAN NOT NULL DEFAULT 0 COMMENT '',
    @Column(name="timeslot3_taken")
    private boolean timeslot3_taken = false;
    public boolean isTimeslot3_taken() {
        return timeslot3_taken;
    }

    public void setTimeslot3_taken(boolean timeslot3_taken) {
        this.timeslot3_taken = timeslot3_taken;
    }

    // `timeslot4_taken` BOOLEAN NOT NULL DEFAULT 0 COMMENT '',
    @Column(name="timeslot4_taken")
    private boolean timeslot4_taken = false;
    public boolean isTimeslot4_taken() {
        return timeslot4_taken;
    }

    public void setTimeslot4_taken(boolean timeslot4_taken) {
        this.timeslot4_taken = timeslot4_taken;
    }

    // `timeslot5_taken` BOOLEAN NOT NULL DEFAULT 0 COMMENT '',
    @Column(name="timeslot5_taken")
    private boolean timeslot5_taken = false;
    public boolean isTimeslot5_taken() {
        return timeslot5_taken;
    }

    public void setTimeslot5_taken(boolean timeslot5_taken) {
        this.timeslot5_taken = timeslot5_taken;
    }    

    // `timeslot6_taken` BOOLEAN NOT NULL DEFAULT 0 COMMENT '',
    @Column(name="timeslot6_taken")
    private boolean timeslot6_taken = false;
    public boolean isTimeslot6_taken() {
        return timeslot6_taken;
    }

    public void setTimeslot6_taken(boolean timeslot6_taken) {
        this.timeslot6_taken = timeslot6_taken;
    }

    // `timeslot7_taken` BOOLEAN NOT NULL DEFAULT 0 COMMENT '',
    @Column(name="timeslot7_taken")
    private boolean timeslot7_taken = false;
    public boolean isTimeslot7_taken() {
        return timeslot7_taken;
    }

    public void setTimeslot7_taken(boolean timeslot7_taken) {
        this.timeslot7_taken = timeslot7_taken;
    }

    public ApplyStudyroomSchedule() {

    }

    public ApplyStudyroomSchedule(int seat, boolean timeslot1_taken,  boolean timeslot2_taken, boolean timeslot3_taken, boolean timeslot4_taken, 
                                    boolean timeslot5_taken, boolean timeslot6_taken, boolean timeslot7_taken) {
        this.seat = seat;
        this.timeslot1_taken = timeslot1_taken;
        this.timeslot2_taken = timeslot2_taken;
        this.timeslot3_taken = timeslot3_taken;
        this.timeslot4_taken = timeslot4_taken;
        this.timeslot5_taken = timeslot5_taken;
        this.timeslot6_taken = timeslot6_taken;
        this.timeslot7_taken = timeslot7_taken;
    }
}
