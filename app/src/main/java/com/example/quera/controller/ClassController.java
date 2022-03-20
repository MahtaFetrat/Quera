package com.example.quera.controller;

import com.example.quera.model.Assignment;
import com.example.quera.model.Course;
import com.example.quera.model.User;

public class ClassController {
    public Course getUserClassByName(User user, String className) {
        return Course.getAllClasses().get(className);
    }

    public Course getClassByName(String name) {
        return Course.getAllClasses().get(name);
    }

    public String getClassAssignments(Course c) {
        return String.join("\n", c.getAssignmentIds());
    }

    public Assignment getClassAssignmentByName(Course clas, String assignment) {
        return Assignment.getAllAssignments().get(Assignment.buildId(clas.getName(), assignment));
    }
}
