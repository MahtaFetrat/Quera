package com.example.quera.controller;

import com.example.quera.model.Assignment;
import com.example.quera.model.Professor;
import com.example.quera.model.Student;
import com.example.quera.model.Class;
import com.example.quera.model.User;

public class ClassController {
    public Class getUserClassByName(User user, String className) {
        for (Class c :
                user.getClasses()) {
            if (c.getName().equals(className))
                return c;
        }
        return null;
    }

    public Class getClassByName(String name) {
        for (Class c :
                Class.allClasses) {
            if (c.getName().equals(name))
                return c;
        }
        return null;
    }

    public String getClassAssignments(Class clas) {
        StringBuilder assignments = new StringBuilder("");
        for (Assignment assignment :
                clas.getAssignments()) {
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
