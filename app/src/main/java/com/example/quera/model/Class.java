package com.example.quera.model;

import java.util.ArrayList;

public class Class {
    public static ArrayList<Class> allClasses;
    protected String name;
    protected Professor professor;
    protected ArrayList<Assignment> assignments;

    public String getName() {
        return name;
    }

    public Professor getProfessor() {
        return professor;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }
}
