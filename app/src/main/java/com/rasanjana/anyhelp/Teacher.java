package com.rasanjana.anyhelp;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private String primary,sixtoOl,Grade;
    private String Location;
    private String time;
    private String qualifications;
    private String description;
    private List<String> subjects = new ArrayList<>();
    private  List<String>grades= new ArrayList<>();

    public Teacher() {
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }
    public List<String> getGrades() {
        return grades;
    }
    public void setGrades(List<String> grades){
        this.grades=grades;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getSixtoOl() {
        return sixtoOl;
    }

    public void setSixtoOl(String sixtoOl) {
        this.sixtoOl = sixtoOl;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String Grade) {
        this.Grade = Grade;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}