package com.backend.payload.request;

import java.time.LocalDate;

public class ApplyJoinRequest {
    private String studentNo;

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    private String english_name;

    public String getEnglish_name() {
        return english_name;
    }

    public void setEnglish_name(String english_name) {
        this.english_name = english_name;
    }

    private String chinese_name;

    public String getChinese_name() {
        return chinese_name;
    }

    public void setChinese_name(String chinese_name) {
        this.chinese_name = chinese_name;
    }
    
    private int grade;

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }    

    private char gender;

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }    

    private String nationality;

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }        

    private String major;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }        
    
    private String student_status;

    public String getStudent_status() {
        return student_status;
    }

    public void setStudent_status(String student_status) {
        this.student_status = student_status;
    }    

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }    

    private String guardian_name;

    public String getGuardian_name() {
        return guardian_name;
    }

    public void setGuardian_name(String guardian_name) {
        this.guardian_name = guardian_name;
    } 
    
    private String guardian_relation;

    public String getGuardian_relation() {
        return guardian_relation;
    }

    public void setGuardian_relation(String guardian_relation) {
        this.guardian_relation = guardian_relation;
    }    

    private String guardian_phone;

    public String getGuardian_phone() {
        return guardian_phone;
    }

    public void setGuardian_phone(String guardian_phone) {
        this.guardian_phone = guardian_phone;
    }    
    
    private String landline;

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }    

    private int hope_fac_1;

    public int getHope_fac_1() {
        return hope_fac_1;
    }

    public void setHope_fac_1(int hope_fac_1) {
        this.hope_fac_1 = hope_fac_1;
    }
    
    private int hope_fac_2;

    public int getHope_fac_2() {
        return hope_fac_2;
    }

    public void setHope_fac_2(int hope_fac_2) {
        this.hope_fac_2 = hope_fac_2;
    }  
    
    private int hope_fac_3;

    public int getHope_fac_3() {
        return hope_fac_3;
    }

    public void setHope_fac_3(int hope_fac_3) {
        this.hope_fac_3 = hope_fac_3;
    }  

    private int hope_fac_4;

    public int getHope_fac_4() {
        return hope_fac_4;
    }

    public void setHope_fac_4(int hope_fac_4) {
        this.hope_fac_4 = hope_fac_4;
    }
    
    private int hope_fac_5;

    public int getHope_fac_5() {
        return hope_fac_5;
    }

    public void setHope_fac_5(int hope_fac_5) {
        this.hope_fac_5 = hope_fac_5;
    }
    
    private int hope_fac_6;

    public int getHope_fac_6() {
        return hope_fac_6;
    }

    public void setHope_fac_6(int hope_fac_6) {
        this.hope_fac_6 = hope_fac_6;
    }  

    private String period;

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }       

    private LocalDate date_join;

    public LocalDate getDate_join() {
        return date_join;
    }

    public void setDate_join(LocalDate date_join) {
        this.date_join = date_join;
    }   
   
    private boolean single_yn;

    public boolean getSingle_yn() {
        return single_yn;
    }

    public void setSingle_yn(boolean single_yn) {
        this.single_yn = single_yn;
    }
    
    private boolean pri_ent;

    public boolean getPri_ent() {
        return pri_ent;
    }

    public void setPri_ent(boolean pri_ent) {
        this.pri_ent = pri_ent;
    }   
    
    private boolean fgn_mate;

    public boolean getFgn_mate() {
        return fgn_mate;
    }

    public void setFgn_mate(boolean fgn_mate) {
        this.fgn_mate = fgn_mate;
    }
    
    private String accpm_ent;

    public String getAccpm_ent() {
        return accpm_ent;
    }

    public void setAccpm_ent(String accpm_ent) {
        this.accpm_ent = accpm_ent;
    }    

    private boolean par_mes_yn;

    public boolean getPar_mes_yn() {
        return par_mes_yn;
    }

    public void setPar_mes_yn(boolean par_mes_yn) {
        this.par_mes_yn = par_mes_yn;
    }    

}
