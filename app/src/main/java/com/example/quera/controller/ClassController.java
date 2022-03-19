package com.example.quera.controller;

import com.example.quera.model.Assignment;
import com.example.quera.model.Course;
import com.example.quera.model.User;

public class ClassController {
    public Course getUserClassByName(User user, String className) {
        return Course.allClasses.get(className);
    }

    public Course getClassByName(String name) {
        return Course.getAllClasses().get(name);
    }

    public String getClassAssignments(Course c) {
        StringBuilder assignments = new StringBuilder("");
        for (Assignment assignment :
                c.getAssignments()) {
            assignments.append(assignment.getName());
        }
        return assignments.toString();
    }

    public Assignment getClassAssignmentByName(Course clas, String assignment) {
        for (Assignment a :
                clas.getAssignments()) {
            if (assignment.equals(a.getName()))
                return a;
        }
        return null;
    }
}
