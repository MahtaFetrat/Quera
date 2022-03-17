package com.example.queratest.controll;

import com.example.queratest.module.Assignment;
import com.example.queratest.module.Professor;
import com.example.queratest.module.Student;
import com.example.queratest.module.Class;
import com.example.queratest.module.User;

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
