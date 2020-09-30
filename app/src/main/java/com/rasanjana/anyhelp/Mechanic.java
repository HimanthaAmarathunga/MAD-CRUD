package com.rasanjana.anyhelp;

import java.util.ArrayList;
import java.util.List;

public class Mechanic {
    private String Location;
    private String Time;
    private String Qualifications;
    private String Description;
    private List<String> Fields = new ArrayList<>();

    public Mechanic() {
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
}
