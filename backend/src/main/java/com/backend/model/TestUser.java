package com.backend.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_user")

// CREATE TABLE IF NOT EXISTS `dormitory`.`test_user` (
//   `id` VARCHAR(200) NOT NULL,
//   `no` INT NOT NULL COMMENT '학번',
//   PRIMARY KEY (`id`)
// )

public class TestUser {
    @Id
    private String id;
    @Column(name = "no")
    private int no; 

    public TestUser() {

    }

    public TestUser(String id, int no) {
        this.id = id;
        this.no = no;
    }

    @Override
    public String toString() {
        return "TestUser " + id + ": {id: " + id + ", no: " + no + "}";
    }
}
