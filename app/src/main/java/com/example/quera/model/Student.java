package com.example.quera.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User {
    public static Student loggedStudent;
    protected String studentNumber;
    protected ArrayList<Assignment> assignments;
    protected ArrayList<Answer> answers;
    private static HashMap<String, Student> allStudents = new HashMap<>();

    public Student(String username, String password, String firstname, String lastname, String studentNumber) {
        super(username, password, firstname, lastname);
        this.studentNumber = studentNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public static Student getLoggedStudent() { return loggedStudent; }

    public static void studentLogin(Student student) {
        loggedStudent = student;
    }

    public void addAssignment(Assignment assignment) {
        this.assignments.add(assignment);
    }

    public void answerAssignment(Assignment assignment, String answer) {
        Answer studentAnswer = new Answer(this, assignment, answer);
        this.answers.add(studentAnswer);
    }

    public void editAnswer(Answer answer, String newAnswer) {
        answer.setAnswer(newAnswer);
    }

    public Answer getAnswerByAssignment(String assignmentName) {
        for (Answer answer : answers) {
            if (answer.getAssignment().getName().equals(assignmentName)) {
                return answer;
            }
        }
        return null;
    }

    public static HashMap<String, Student> getAllStudents() {
        return allStudents;
    }

    public static void setAllStudents(HashMap<String, Student> allStudents) {
        Student.allStudents = allStudents;
    }
}
