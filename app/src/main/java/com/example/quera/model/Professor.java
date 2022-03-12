package com.example.quera.model;

public class Professor extends User {
    protected String university;

    public Professor(String username, String password, String firstname, String lastname, String university) {
        super(username, password, firstname, lastname);
        this.university = university;
    }
}
