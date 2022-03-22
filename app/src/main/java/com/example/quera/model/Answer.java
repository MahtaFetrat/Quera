package com.example.quera.model;

import java.util.HashMap;

public class Answer {
    protected String id;
    protected String studentId;
    protected String assignmentId;
    protected String answer;
    protected float grade;
    protected static HashMap<String, Answer> allAnswers = new HashMap<>();

    public Answer(Student student, Assignment assignment, String answer) {
        this.id = buildId(student, assignment);
        this.studentId = student.getUsername();
        this.assignmentId = assignment.getId();
        this.answer = answer;
        allAnswers.put(id, this);
    }

    public static Answer getAnswerById(String answerId) {
        return allAnswers.get(answerId);
    }

    public static HashMap<String, Answer> getAllAnswers() {
        return allAnswers;
    }

    public static void setAllAnswers(HashMap<String, Answer> allAnswers) {
        Answer.allAnswers = allAnswers;
    }

    public String getAnswer() {
        return answer;
    }

    public Assignment getAssignment() {
        return Assignment.getAssignmentById(assignmentId);
    }

    public Student getStudent() {
        return Student.getStudentById(studentId);
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

    public static String buildId(Student student, Assignment assignment) {
        return student.getUsername() + assignment.getId();
    }
}
