package com.example.quera.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User {
    public static Student loggedStudent;
    protected String studentNumber;
    private static HashMap<String, Student> allStudents = new HashMap<>();

    public Student(String username, String password, String firstname, String lastname, String studentNumber) {
        super(username, password, firstname, lastname);
        this.studentNumber = studentNumber;
    }

    public static Student getStudentById(String studentId) {
        return allStudents.get(studentId);
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public static Student getLoggedStudent() { return loggedStudent; }

    public static void studentLogin(Student student) {
        loggedStudent = student;
    }

    public void editAnswer(Answer answer, String newAnswer) {
        answer.setAnswer(newAnswer);
    }

    public static HashMap<String, Student> getAllStudents() {
        return allStudents;
    }

    public static void setAllStudents(HashMap<String, Student> allStudents) {
        Student.allStudents = allStudents;
    }
}
