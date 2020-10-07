package com.rasanjana.anyhelp;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.List;

public class Mechanic {
    private String Name;
    private String ContactNo;
    private String Location;
    private String Time;
    private String Qualifications;
    private String Description;
    private List<String> Fields = new ArrayList<>();
    private List<MyAppointments> appointments = new ArrayList<>();

    @Exclude
    private String key;
    @Exclude
    private String Profession;

    public Mechanic() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public List<String> getFields() {
        return Fields;
    }

    public void setFields(List<String> fields) {
        Fields = fields;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getQualifications() {
        return Qualifications;
    }

    public void setQualifications(String qualifications) {
        Qualifications = qualifications;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<MyAppointments> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<MyAppointments> appointments) {
        this.appointments = appointments;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getProfession() {
        return Profession;
    }

    public void setProfession(String profession) {
        Profession = profession;
    }
}
