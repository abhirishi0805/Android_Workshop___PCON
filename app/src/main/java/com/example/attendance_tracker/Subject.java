package com.example.attendance_tracker;

public class Subject {
    String id;
    String subject;
    int totalpresent;
    int totalclass;
    int minimumpercentage;

    public Subject(){

    }

    public Subject(String id, String subject, int totalpresent, int totalclass, int minimumpercentage) {
        this.id = id;
        this.subject = subject;
        this.totalpresent = totalpresent;
        this.totalclass = totalclass;
        this.minimumpercentage = minimumpercentage;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getTotalpresent() {
        return totalpresent;
    }

    public void setTotalpresent(int totalpresent) {
        this.totalpresent = totalpresent;
    }

    public int getTotalclass() {
        return totalclass;
    }

    public void setTotalclass(int totalclass) {
        this.totalclass = totalclass;
    }

    public int getMinimumpercentage() {
        return minimumpercentage;
    }

    public void setMinimumpercentage(int minimumpercentage) {
        this.minimumpercentage = minimumpercentage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
