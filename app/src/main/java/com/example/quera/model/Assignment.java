package com.example.quera.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Assignment {
    public static HashMap<String, Assignment> allAssignments = new HashMap<>();
    protected String id;
    protected String name;
    protected String className;
    protected ArrayList<Answer> answers = new ArrayList<>();

    public Assignment(String name, String className) {
        this.id = name + className;
        this.name = name;
        this.className = className;
        allAssignments.put(id, this);
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Answer> getAnswers() {
        return this.answers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Assignment getAssignmentByName(String assignmentName) {
        return allAssignments.get(assignmentName);
    }

    public static HashMap<String, Assignment> getAllAssignments() {
        return allAssignments;
    }

    public static void setAllAssignments(HashMap<String, Assignment> allAssignments) {
        Assignment.allAssignments = allAssignments;
    }
}
