package com.example.quera.model;

public class Professor extends User {
    protected String university;
    protected int grade;

    public Professor(String username, String password, String firstname, String lastname, String universityName) {
        super(username, password, firstname, lastname);
        university = universityName;
    }
}
