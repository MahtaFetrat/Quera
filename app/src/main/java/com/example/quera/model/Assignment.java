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
        this.id = getId(name, className);
        this.name = name;
        this.className = className;
        allAssignments.put(id, this);
        Course.getAllClasses().get(className).assignmentIds.add(id);
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

    public static String getId(String className, String name) {
        return className + name;
    }
}
