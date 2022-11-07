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

//   `timeslot` INT NOT NULL COMMENT '',
    @Column(name="timeslot")
    private int timeslot;
    public int getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(int timeslot) {
        this.timeslot = timeslot;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "applyConsultSchedule", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    ApplyConsultSchedule applyConsultSchedule;

    public ApplyConsultSchedule getApplyConsultSchedule() {
        return applyConsultSchedule;
    }

    public void setApplyConsultSchedule(ApplyConsultSchedule applyConsultSchedule) {
        this.applyConsultSchedule = applyConsultSchedule;
    }

    @Column(name="approved")
    private boolean approved = false;

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
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

    public ApplyConsult(int timeslot) {
        this.timeslot = timeslot;
    }
    
}
