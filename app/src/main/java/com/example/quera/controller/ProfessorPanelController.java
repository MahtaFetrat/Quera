package com.example.quera.controller;

import com.example.quera.model.Professor;
import com.example.quera.model.Course;


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

    public Course getProfessorClassByName(Professor professor, String className) {
        return Course.allClasses.get(className);
    }

    public void createClass (String name, Professor professor) {
        Course c = new Course(name, professor);
        professor.getClassNames().add(c.getName());
        Course.allClasses.put(c.getName(), c);
        ;
    }
}
