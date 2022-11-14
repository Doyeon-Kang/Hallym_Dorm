package com.backend.payload.request;

import java.time.LocalDate;

public class ApplyResignRequest {

    private String studentNo;

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    private LocalDate res_date;

    public LocalDate getRes_date() {
        return res_date;
    }

    public void setRes_date(LocalDate res_date) {
        this.res_date = res_date;
    }

    private String res_reason;

    public String getRes_reason() {
        return res_reason;
    }

    public void setRes_reason(String res_reason) {
        this.res_reason = res_reason;
    }
}
