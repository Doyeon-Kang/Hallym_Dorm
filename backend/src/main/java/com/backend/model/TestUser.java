package com.backend.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "schedule")

// `sch_id` INT NOT NULL COMMENT '일정아이디',
//   `title` VARCHAR(200) NULL COMMENT '일정제목',
//   `color` VARCHAR(10) NULL COMMENT '색상',
//   `st_date` DATE NULL COMMENT '시작일자',
//   `ed_date` DATE NULL COMMENT '종료일자',
//   PRIMARY KEY (`sch_id`))

public class TestUser {
    @Id
    private int sch_id;
    @Column(name = "title")
    private String title;
    @Column(name = "color")
    private String color;
    @Column(name = "st_date")
    private Date st_date;
    @Column(name = "ed_date")
    private Date ed_date;

    public TestUser() {

    }

    public TestUser(int sch_id, String title) {
        this.sch_id = sch_id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Schedule: {sch_id: " + sch_id + ", title: " + title + "}";
    }
}
