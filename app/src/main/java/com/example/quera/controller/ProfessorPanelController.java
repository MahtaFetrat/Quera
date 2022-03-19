package com.example.quera.controller;

import com.example.quera.model.Professor;
import com.example.quera.model.Student;
import com.example.quera.model.Class;

import java.util.ArrayList;

public class ProfessorPanelController {
    public String getProfessorClassNames(Professor professor){
        return String.join(", ", professor.getClassNames());
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
        return Class.allClasses.get(className);
    }

    public void createClass (String name, Professor professor) {
        Class c = new Class(name, professor);
        professor.getClassNames().add(c.getName());
        Class.allClasses.put(c.getName(), c);
    }
}
