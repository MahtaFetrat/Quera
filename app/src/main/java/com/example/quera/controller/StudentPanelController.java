package com.example.quera.controller;

import com.example.quera.model.Student;
import com.example.quera.model.Class;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentPanelController {
    public String getStudentClassNames(Student student){
        return String.join(", ", student.getClassNames());
    }

    public String getClassNamesStudentCanJoin(Student student){
        HashMap<String, Class> classes = new HashMap<>();
        classes.putAll(Class.getAllClasses());
        classes.keySet().removeIf(k -> student.getClassNames().contains(k));
        return String.join(", ", classes.keySet());
    }

    public void addStudentToClass(Student student, Class c){
        student.addStudentToClass(c.getName());
    }

    public Student getStudentByUsername(String username) {
        return Student.getAllStudents().get(username);
    }

    public Class getStudentClassByName(Student student, String className) {
        return Class.allClasses.get(className);
    }
}
