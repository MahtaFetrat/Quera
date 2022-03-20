package com.example.quera.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Assignment {
    public static HashMap<String, Assignment> allAssignments = new HashMap<>();
    protected String id;
    protected String name;
    protected String className;
    protected ArrayList<String> answerIds = new ArrayList<>();

    public Assignment(String name, String className) {
        this.id = buildId(name, className);
        this.name = name;
        this.className = className;
        allAssignments.put(id, this);
        Course.getAllClasses().get(className).assignmentIds.add(id);
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Answer> getAnswers() {
        ArrayList<Answer> answers = new ArrayList<>();
        for (String answerId : answerIds) {
            answers.add(Answer.getAnswerById(answerId));
        }
        return answers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Assignment getAssignmentById(String assignmentId) {
        return allAssignments.get(assignmentId);
    }

    public static HashMap<String, Assignment> getAllAssignments() {
        return allAssignments;
    }

    public static void setAllAssignments(HashMap<String, Assignment> allAssignments) {
        Assignment.allAssignments = allAssignments;
    }

    public static String buildId(String className, String name) {
        return className + name;
    }

    public String getId() {
        return id;
    }

    public Answer getStudentAnswer(String username) {
        for (Answer answer : getAnswers()) {
            if (answer.studentId == username) {
                return answer;
            }
        }
        return null;
    }

    public void addAnswer(Answer answer) {
        answerIds.add(answer.id);
    }
}
