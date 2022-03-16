package com.example.quera.model;

import java.util.ArrayList;

public class Assignment {
    protected String name;
    protected Class className;
    protected ArrayList<Answer> answers;
    protected ArrayList<Assignment> assignments;

    public Assignment(String name, Class className) {
        this.name = name;
        this.className = className;
        assignments.add(this);
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Assignment> getAssignments() {
        return this.assignments;
    }

    public ArrayList<Answer> getAnswers() {
        return this.answers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Assignment findAssignment(String assignmentName) {
        for (Assignment assignment : assignments)
            if (assignment.getName().equals(assignmentName)) {
                return assignment;
            }
        return null;
    }
}
