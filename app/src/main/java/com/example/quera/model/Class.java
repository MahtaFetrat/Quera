package com.example.quera.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Class {
    public static HashMap<String, Class> allClasses = new HashMap<>();
    protected String name;
    protected Professor professor;
    protected ArrayList<Assignment> assignments = new ArrayList<>();
    
    public Class(String name, Professor professor) {
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

    public static HashMap<String, Class> getAllClasses() {
        return allClasses;
    }

    // TODO: check for class name uniqueness
}
