package com.example.firstproject;

import java.util.ArrayList;

public class Class {
    private ArrayList<String> assignments = new ArrayList<>();
    private ArrayList<String> exams = new ArrayList<>();
    private String instructor;
    private String location;
    private String time;

    private String name;

    public Class(String name, String instructor, String location, String time) {
        this.name = name;
        this.instructor = instructor;
        this.location = location;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    public void setName(String name) {this.name = name;}

    public void setInstructor(String instructor) { this.instructor = instructor; }

    public void setLocation(String location) { this.location = location; }

    public void setTime(String time) { this.time = time; }

    public void deleteAssignment(String assignment) {assignments.remove(assignment);}

    public void addAssignment(String assignment) {
        assignments.add(assignment);
    }

    public ArrayList<String> getAssignments() {
        return assignments;
    }

    public void addExams(String exam) { exams.add(exam); }

    public void deleteExam(String exam) { exams.remove(exam); }

    public ArrayList<String> getExams() { return exams; }
}
