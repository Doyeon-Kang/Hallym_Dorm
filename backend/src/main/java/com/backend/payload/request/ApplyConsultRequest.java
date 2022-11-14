package com.backend.payload.request;

public class ApplyConsultRequest {
    private String studentNo;

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    private String dayOfWeek;

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    private int[] timeslots;

    public int[] getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(int[] timeslots) {
        this.timeslots = timeslots;
    }

    //     public ApplyConsult(String topic, String subject) {
    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
