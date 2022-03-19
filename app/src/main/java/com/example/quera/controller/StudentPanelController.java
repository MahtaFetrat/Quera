package com.example.quera.controller;

import com.example.quera.model.Student;
import com.example.quera.model.Class;

import java.util.ArrayList;

public class StudentPanelController {
    public String getClassesName(ArrayList<Class> classes){
        StringBuilder names = new StringBuilder();
        for (Class c : classes) {
            names.append(c.getName());
        }
        return names.toString();
    }

    public String getStudentClassNames(Student student){
        return this.getClassesName(student.getClasses());
    }

    public String getClassNamesStudentCanJoin(Student student){
        ArrayList<Class> classes = new ArrayList<>(Class.allClasses);
        classes.removeAll(student.getClasses());
        return this.getClassesName(classes);
    }

    public void addStudentToClass(Student student, Class c){
        student.addStudentToClass(c);
    }

    public Student getStudentByUsername(String username) {
        for (Student s :
                Student.getAllStudents().values()) {
            if (s.getUsername().equals(username)) {
                return s;
            }
        }
        return null;
    }

    public Class getStudentClassByName(Student student, String className) {
        for (Class c :
                student.getClasses()) {
            if (className.equals(c.getName()))
                return c;
        }
        return null;
    }
}
