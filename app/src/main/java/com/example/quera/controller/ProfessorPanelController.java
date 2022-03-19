package com.example.quera.controller;

import com.example.quera.model.Professor;
import com.example.quera.model.Student;
import com.example.quera.model.Class;

import java.util.ArrayList;

public class ProfessorPanelController {
    public String getClassesName(ArrayList<Class> classes){
        StringBuilder names = new StringBuilder();
        for (Class c : classes) {
            names.append(c.getName());
        }
        return names.toString();
    }

    public String getProfessorClassNames(Professor professor){
        return this.getClassesName(professor.getClasses());
    }

    public Professor getProfessorByUsername(String username) {
        for (Professor professor :
                Professor.getAllProfessors().values()) {
            if (professor.getUsername().equals(username)) {
                return professor;
            }
        }
        return null;
    }

    public Class getProfessorClassByName(Professor professor, String className) {
        for (Class c :
                professor.getClasses()) {
            if (className.equals(c.getName()))
                return c;
        }
        return null;
    }

    public void createClass (String name, Professor professor) {
        Class c = new Class(name, professor);
        professor.getClasses().add(c);
        Class.allClasses.add(c);
    }
}
