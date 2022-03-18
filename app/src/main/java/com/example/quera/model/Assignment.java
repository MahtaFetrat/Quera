package com.example.quera.model;

import java.util.ArrayList;

public class Assignment {
    public static ArrayList<Assignment> allAssignments;
    protected String name;
    protected Class className;
    protected ArrayList<Answer> answers;

    public Assignment(String name, Class className) {
        this.name = name;
        this.className = className;
        allAssignments.add(this);
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Answer> getAnswers() {
        return this.answers;
    }

    public static ArrayList<Assignment> getAssignments() {
        return allAssignments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Assignment getAssignmentByName(String assignmentName) {
        for (Assignment assignment : allAssignments)
            if (assignment.getName().equals(assignmentName)) {
                return assignment;
            }
        return null;
    }
}
