package com.example.quera.model;

import androidx.annotation.NonNull;

public class Answer {
    protected Student student;
    protected Assignment assignment;
    protected String answer;
    protected float grade;

    public Answer(Student student, Assignment assignment, String answer) {
        this.student = student;
        this.assignment = assignment;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public Student getStudent() {
        return student;
    }

    public float getGrade() {
        return grade;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
