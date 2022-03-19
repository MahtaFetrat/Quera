package com.example.quera.controller;

import com.example.quera.model.Student;
import com.example.quera.model.Course;

import java.util.HashMap;

public class StudentPanelController {
    public String getStudentClassNames(Student student){
        return String.join("\n", student.getClassNames());
    }

    public String getClassNamesStudentCanJoin(Student student){
        HashMap<String, Course> classes = new HashMap<>();
        classes.putAll(Course.getAllClasses());
        classes.keySet().removeIf(k -> student.getClassNames().contains(k));
        return String.join("\n", classes.keySet());
    }

    public void addStudentToClass(Student student, Course c){
        student.addStudentToClass(c.getName());
    }

    public Student getStudentByUsername(String username) {
        return Student.getAllStudents().get(username);
    }

    public Course getStudentClassByName(Student student, String className) {
        return Course.getAllClasses().get(className);
    }
}
