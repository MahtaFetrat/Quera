package com.example.quera.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Professor extends User {
    protected String university;
    private static HashMap<String, Professor> allProfessors = new HashMap<>();

    public Professor(String username, String password, String firstname, String lastname, String university) {
        super(username, password, firstname, lastname);
        this.university = university;
    }

    public String getUniversity() {
        return university;
    }

    public void changeAssignment(Assignment assignment, String name) {
        assignment.setName(name);
    }

    public void gradingAnswer(Answer answer, float grade) {
        answer.setGrade(grade);
    }

    public static HashMap<String, Professor> getAllProfessors() {
        return allProfessors;
    }

    public static void setAllProfessors(HashMap<String, Professor> allProfessors) {
        Professor.allProfessors = allProfessors;
    }
}
