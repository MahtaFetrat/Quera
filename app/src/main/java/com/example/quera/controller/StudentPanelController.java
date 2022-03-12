package com.example.quera.controller;

import com.example.quera.model.Student;
import com.example.quera.model.Class;

import java.util.ArrayList;

public class StudentPanelController {
    public ArrayList<String> getClassesName(ArrayList<Class> classes){
        ArrayList<String> names = new ArrayList<>();
        for (Class c : classes) {
            names.add(c.getName());
        }
        return names;
    }

    public ArrayList<String> getStudentClassNames(Student student){
        return this.getClassesName(student.getClasses());
    }

    public ArrayList<String> getClassNamesStudentCanJoin(Student student){
        ArrayList<Class> classes = new ArrayList<>(Class.allClasses);
        classes.removeAll(student.getClasses());
        return this.getClassesName(classes);
    }

    public void addStudentToClass(Student student, Class c){
        student.addStudentToClass(c);
    }
}
