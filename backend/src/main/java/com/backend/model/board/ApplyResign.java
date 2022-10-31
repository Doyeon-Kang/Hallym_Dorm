package com.backend.model.board;

import java.sql.Date;
import java.time.LocalDateTime;

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
@Table(name = "apply_resign")
public class ApplyResign {
    // `id` INT NOT NULL COMMENT '아이디',
    @Id
    @NotBlank
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apply_resign_generator")
    @Column(name="id")
    private Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//  `res_date` DATE NULL COMMENT '퇴사 예정일',
    @Column(name="res_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date res_date;
    
    public Date getRes_date() {
        return res_date;
    }

    public void setRes_date(Date res_date) {
        this.res_date = res_date;
    }

    // `res_reason` VARCHAR(200) NULL COMMENT '퇴사 사유',
    @Column(name="res_reason")
    private String res_reason;

    public String getRes_reason() {
        return res_reason;
    }

    public void setRes_reason(String res_reason) {
        this.res_reason = res_reason;
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



    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name="approved")
    private boolean approved = false;


    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public ApplyResign() {

    }

    public ApplyResign(Date res_date, String res_reason) {
        this.res_date = res_date;
        this.res_reason = res_reason;
    }
}