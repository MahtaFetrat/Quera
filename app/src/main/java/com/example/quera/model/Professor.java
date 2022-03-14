package com.example.quera.model;

import java.util.ArrayList;

public class Professor extends User {
    protected String university;
    protected ArrayList<Assignment> assignments;

    public Professor(String username, String password, String firstname, String lastname, String university) {
        super(username, password, firstname, lastname);
        this.university = university;
    }

    public String getUniversity() {
        return university;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public void newAssignment(String name) {
        Assignment assignment = new Assignment(name);
        assignments.add(assignment);
    }

    public void changeAssignment(Assignment assignment, String name) {
        assignment.setName(name);
    }

    public void gradingAnswer(Answer answer, float grade) {
        answer.setGrade(grade);
    }
}
