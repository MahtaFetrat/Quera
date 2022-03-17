package com.example.quera.model;

import java.util.ArrayList;

public class Class {
    public static ArrayList<Class> allClasses;
    protected String name;
    protected Professor professor;
    protected ArrayList<Assignment> assignments;
    
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
}
