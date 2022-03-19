package com.example.quera.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Course {
    public static HashMap<String, Course> allClasses = new HashMap<>();
    protected String name;
    protected Professor professor;
    protected ArrayList<Assignment> assignments = new ArrayList<>();
    
    public Course(String name, Professor professor) {
        this.name = name;
        this.professor = professor;
        this.assignments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Professor getProfessor() {
        return professor;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public void addAssignment(Assignment assignment) {
        this.assignments.add(assignment);
    }

    public static HashMap<String, Course> getAllClasses() {
        return allClasses;
    }

    public static void setAllClasses(HashMap<String, Course> allClasses) {
        Course.allClasses = allClasses;
    }
}
