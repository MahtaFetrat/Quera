package com.example.quera.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Course {
    private static HashMap<String, Course> allClasses = new HashMap<>();
    protected String name;
    protected Professor professor;
    protected ArrayList<String> assignmentIds = new ArrayList<>();
    
    public Course(String name, Professor professor) {
        this.name = name;
        this.professor = professor;
    }

    public String getName() {
        return name;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void addAssignment(String assignmentId) {
        this.assignmentIds.add(assignmentId);
    }

    public static HashMap<String, Course> getAllClasses() {
        return allClasses;
    }

    public static void setAllClasses(HashMap<String, Course> allClasses) {
        Course.allClasses = allClasses;
    }

    public ArrayList<String> getAssignmentIds() {
        return assignmentIds;
    }

    public ArrayList<Assignment> getAssignments() {
        ArrayList<Assignment> assignments = new ArrayList<>();
        for (Assignment assignment : Assignment.getAllAssignments().values()) {
            if (assignment.className == name){
                assignments.add(assignment);
            }
        }
        return assignments;
    }
}
