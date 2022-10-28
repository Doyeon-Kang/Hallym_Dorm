package com.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user_member")
public class UserMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//   `english_name` VARCHAR(50) COMMENT '영문이름',
    @Column(name="english_name")
    private String english_name;

    public String getEnglish_name() {
        return english_name;
    }

    public void setEnglish_name(String english_name) {
        this.english_name = english_name;
    }

// `chinese_name` VARCHAR(10) COMMENT '한자',
    @Column(name="chinese_name")
    private String chinese_name;
    public String getChinese_name() {
        return chinese_name;
    }

    public void setChinese_name(String chinese_name) {
        this.chinese_name = chinese_name;
    }

// `grade` INT NOT NULL COMMENT '학년',
    @Column(name="grade")
    private int grade;
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

// `gender` VARCHAR(1) COMMENT '성별',
    @Column(name="gender")
    private char gender;
    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

// `nationality` VARCHAR(20) COMMENT '국적',
    @Column(name="nationality")
    private String nationality;
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

// `department` VARCHAR(50) COMMENT '대학',
    @Column(name="department")
    private String department;
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

// `major` VARCHAR(50) COMMENT '학과',
    @Column(name="major")
    private String major;
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

// `student_status` VARCHAR(10) COMMENT '학적상태',
    @Column(name="student_status")
    private String student_status;
    public String getStudent_status() {
        return student_status;
    }

    public void setStudent_status(String student_status) {
        this.student_status = student_status;
    }

// `phone` VARCHAR(20) COMMENT '연락처',
    @Column(name="phone")
    private String phone;
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

// `address` VARCHAR(100) COMMENT '주소',
    @Column(name="address")
    private String address;
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

// `guardian_name` VARCHAR(50) COMMENT '보호자 성명',
    @Column(name="guardian_name")
    private String guardian_name;
    public String getGuardian_name() {
        return guardian_name;
    }

    public void setGuardian_name(String guardian_name) {
        this.guardian_name = guardian_name;
    }

// `guardian_relation` VARCHAR(15) COMMENT '보호자 관계',
    @Column(name="guardian_relation")
    private String guardian_relation;
    public String getGuardian_relation() {
        return guardian_relation;
    }

    public void setGuardian_relation(String guardian_relation) {
        this.guardian_relation = guardian_relation;
    }

// `guardian_phone` VARCHAR(20) COMMENT '보호자 연락처',
    @Column(name="guardian_phone")
    private String guardian_phone;
    public String getGuardian_phone() {
        return guardian_phone;
    }

    public void setGuardian_phone(String guardian_phone) {
        this.guardian_phone = guardian_phone;
    }

// `landline` VARCHAR(20) COMMENT '자택 전화',
    @Column(name="landline")
    private String landline;
    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

//   `point` INT NULL COMMENT '상/벌점',
    @Column(name="point")
    private int point;

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

//   `res_fac` INT NULL COMMENT '거주관',
    @Column(name="res_fac")
    private int res_fac;

    public int getRes_fac() {
        return res_fac;
    }

    public void setRes_fac(int res_fac) {
        this.res_fac = res_fac;
    }

//   `res_room` INT NULL COMMENT '거주호실',
    @Column(name="res_room") 
    private int res_room;

    public int getRes_room() {
        return res_room;
    }

    public void setRes_room(int res_room) {
        this.res_room = res_room;
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

    public UserMember() {

    }

    public UserMember(String english_name, String chinese_name, int grade, char gender,
                        String nationality, String department, String major, String student_status,
                        String phone, String address, String guardian_name, String guardian_relation,
                        String guardian_phone, String landline, int res_fac, int res_room) {
        this.english_name = english_name;
        this.chinese_name = chinese_name;
        this.grade = grade;
        this.gender = gender;
        this.nationality = nationality;
        this.department = department;
        this.major = major;
        this.student_status = student_status;
        this.phone = phone;
        this.address = address;
        this.guardian_name = guardian_name;
        this.guardian_relation = guardian_relation;
        this.guardian_phone = guardian_phone;
        this.landline = landline;
        this.res_fac = res_fac;
        this.res_room = res_room;
    }
}
