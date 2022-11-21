package com.backend.payload.request;

public class ApplyStudyroomRequest {
    // public ApplyStudyroom(int timeslot1, int timeslot2, int timeslot3) {
    private String studentNo;

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    private int seatNo;
    

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    private int[] timeslots;

    public int[] getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(int[] timeslots) {
        this.timeslots = timeslots;
    }
    
}
