package com.backend.model.board;

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
@Table(name = "apply_join")
public class ApplyJoin {
    // `id` INT NOT NULL COMMENT '아이디',
    @Id
    @NotBlank
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apply_join_generator")
    @Column(name="id")
    private Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

// `english_name` VARCHAR(50) COMMENT '영문',
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

// `hope_fac_1` INT NULL COMMENT '희망시설1',
    @Column(name="hope_fac_1")
    private int hope_fac_1;
    public int getHope_fac_1() {
        return hope_fac_1;
    }

    public void setHope_fac_1(int hope_fac_1) {
        this.hope_fac_1 = hope_fac_1;
    }

// `hope_fac_2` INT NULL COMMENT '희망시설2',
    @Column(name="hope_fac_2")
    private int hope_fac_2;
    public int getHope_fac_2() {
        return hope_fac_2;
    }

    public void setHope_fac_2(int hope_fac_2) {
        this.hope_fac_2 = hope_fac_2;
    }

// `hope_fac_3` INT NULL COMMENT '희망시설3',
    @Column(name="hope_fac_3")
    private int hope_fac_3;
    public int getHope_fac_3() {
        return hope_fac_3;
    }

    public void setHope_fac_3(int hope_fac_3) {
        this.hope_fac_3 = hope_fac_3;
    }
// `hope_fac_4` INT NULL COMMENT '희망시설4',
    @Column(name="hope_fac_4")
    private int hope_fac_4;
    public int getHope_fac_4() {
        return hope_fac_4;
    }

    public void setHope_fac_4(int hope_fac_4) {
        this.hope_fac_4 = hope_fac_4;
    }

// `hope_fac_5` INT NULL COMMENT '희망시설5',
    @Column(name="hope_fac_5")
    private int hope_fac_5;
    public int getHope_fac_5() {
        return hope_fac_5;
    }

    public void setHope_fac_5(int hope_fac_5) {
        this.hope_fac_5 = hope_fac_5;
    }
// `hope_fac_6` INT NULL COMMENT '희망시설6',
    @Column(name="hope_fac_6")
    private int hope_fac_6;
    public int getHope_fac_6() {
        return hope_fac_6;
    }

    public void setHope_fac_6(int hope_fac_6) {
        this.hope_fac_6 = hope_fac_6;
    }
// `period` VARCHAR(15) NULL COMMENT '사용기간',
    @Column(name="period")
    private String period;
    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

// `single_yn` BOOLEAN NULL COMMENT '1인실 사용신청',
    @Column(name="single_yn")
    private boolean single_yn;
    public boolean isSingle_yn() {
        return single_yn;
    }

    public void setSingle_yn(boolean single_yn) {
        this.single_yn = single_yn;
    }

// `pri_ent` BOOLEAN NULL COMMENT '우선입사 대상자',
    @Column(name="pri_ent")
    private boolean pri_ent;
    public boolean isPri_ent() {
        return pri_ent;
    }

    public void setPri_ent(boolean pri_ent) {
        this.pri_ent = pri_ent;
    }

// `fgn_mate` BOOLEAN NULL COMMENT '외국인 룸메이트',
    @Column(name="fgn_mate")
    private boolean fgn_mate;
    public boolean isFgn_mate() {
        return fgn_mate;
    }

    public void setFgn_mate(boolean fgn_mate) {
        this.fgn_mate = fgn_mate;
    }
    
// `accpm_ent` VARCHAR(50) NULL COMMENT '동반 입실자',
    @Column(name="accpm_ent")
    private String accpm_ent;
    public String getAccpm_ent() {
        return accpm_ent;
    }

    public void setAccpm_ent(String accpm_ent) {
        this.accpm_ent = accpm_ent;
    }

// `par_mes_yn` BOOLEAN NULL COMMENT '보호자 문자 수신사항',
    @Column(name="par_mes_yn")
    private boolean par_mes_yn;
    public boolean isPar_mes_yn() {
        return par_mes_yn;
    }

    public void setPar_mes_yn(boolean par_mes_yn) {
        this.par_mes_yn = par_mes_yn;
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

    public ApplyJoin() {

    }

    public ApplyJoin(String english_name, String chinese_name, int grade, char gender,
                        String nationality, String department, String major, String student_status,
                        String phone, String address, String guardian_name, String guardian_relation,
                        String guardian_phone, String landline, int hope_fac_1, int hope_fac_2,
                        int hope_fac_3, int hope_fac_4, int hope_fac_5, int hope_fac_6,
                        String period, boolean single_yn, boolean pri_ent, boolean fgn_mate,
                        String accpm_ent, boolean par_mes_yn) {
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
        this.hope_fac_1 = hope_fac_1;
        this.hope_fac_2 = hope_fac_2;
        this.hope_fac_3 = hope_fac_3;
        this.hope_fac_4 = hope_fac_4;
        this.hope_fac_5 = hope_fac_5;
        this.hope_fac_6 = hope_fac_6; 
        this.period = period;
        this.single_yn = single_yn;
        this.pri_ent = pri_ent;
        this.fgn_mate = fgn_mate;
        this.accpm_ent = accpm_ent;
        this.par_mes_yn = par_mes_yn;
    }
}
