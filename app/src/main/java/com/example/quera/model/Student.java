package com.example.quera.model;

public class Student extends User {
    protected String studentNumber;

    public Student(String username, String password, String firstname, String lastname, String studentNumber) {
        super(username, password, firstname, lastname);
        this.studentNumber = studentNumber;
    }
}
