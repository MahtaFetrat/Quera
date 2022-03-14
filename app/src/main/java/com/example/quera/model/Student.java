package com.example.quera.model;

import java.util.ArrayList;

public class Student extends User {
    protected String studentNumber;
    protected ArrayList<Assignment> assignments;
    protected ArrayList<Answer> answers;

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
}
