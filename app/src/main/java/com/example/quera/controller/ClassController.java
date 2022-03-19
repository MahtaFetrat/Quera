package com.example.quera.controller;

import com.example.quera.model.Assignment;
import com.example.quera.model.Professor;
import com.example.quera.model.Student;
import com.example.quera.model.Class;
import com.example.quera.model.User;

public class ClassController {
    public Class getUserClassByName(User user, String className) {
        return Class.allClasses.get(className);
    }

    public Class getClassByName(String name) {
        return Class.getAllClasses().get(name);
    }

    public String getClassAssignments(Class c) {
        StringBuilder assignments = new StringBuilder("");
        for (Assignment assignment :
                c.getAssignments()) {
            assignments.append(assignment.getName());
        }
        return assignments.toString();
    }

    public Assignment getClassAssignmentByName(Class clas, String assignment) {
        for (Assignment a :
                clas.getAssignments()) {
            if (assignment.equals(a.getName()))
                return a;
        }
        return null;
    }
}
