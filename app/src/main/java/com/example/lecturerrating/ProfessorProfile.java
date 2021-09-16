package com.example.lecturerrating;

import android.telephony.SignalStrength;

public class ProfessorProfile {

    private String name;
    private String email;
    private String photo;
    private String department;
    private String course;
    private double rate;

    public ProfessorProfile(String name, String email, String photo, String department, double rate,String course) {
        this.name = name;
        this.email = email;
        this.photo = photo;
        this.department = department;
        this.rate = rate;
        this.course = course;
    }

    public ProfessorProfile(String name, String department,String course) {
        this.name = name;
        this.department = department;
        this.course = course;
        this.email = "";
        this.photo = "";
        this.rate = 0; // default value
    }
}
