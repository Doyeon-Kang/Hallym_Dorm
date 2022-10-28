package com.backend.model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "apply_sleepout")
public class ApplySleepout {
    // `id` INT NOT NULL COMMENT '아이디',
    @Id
    @NotBlank
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apply_sleepout_generator")
    @Column(name="id")
    private Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//   `date_sleepout` DATE NULL COMMENT '',
    @Column(name="date_sleepout")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date_sleepout;
    
    public Date getDate_sleepout() {
        return date_sleepout;
    }

    public void setDate_sleepout(Date date_sleepout) {
        this.date_sleepout = date_sleepout;
    }

    // `reason` VARCHAR(200) NULL COMMENT '',
    @Column(name="reason")
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    // `date` DATETIME,
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
    
    //`approved` BOOLEAN COMMENT ''
    @Column(name="approved")
    private boolean approved = false;

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }


 
    public ApplySleepout() {

    }

    public ApplySleepout(Date date_sleepout, String reason) {
        this.date_sleepout = date_sleepout;
        this.reason = reason;
    }
}
