package com.example.quera.controller;

import com.example.quera.model.Professor;
import com.example.quera.model.Student;
import com.example.quera.model.User;

import java.util.HashMap;

public class DataHandler {
    private User currentUser;
    private HashMap<String, User> allUsers = new HashMap<>();

    public boolean isUsernameAvailable(String username) {
        return allUsers.containsKey(username);
    }

    public User login(String username, String password) {
        User user = allUsers.get(username);
        if (user != null && user.passwordMatches(password)) {
            currentUser = user;
            return user;
        }
        return null;
    }

    public void logout() {
        currentUser = null;
    }

    public Student signupStudent(String username, String password, String firstname, String lastname, String studentNumber) {
        Student student = new Student(username, password, firstname, lastname, studentNumber);
        if (!isUsernameAvailable(username)){
            allUsers.put(username, student);
            currentUser = student;
            return student;
        }
        return null;
    }

    public Professor signupInstructor(String username, String password, String firstname, String lastname, String universityName) {
        Professor professor = new Professor(username, password, firstname, lastname, universityName);
        if (!isUsernameAvailable(username)){
            allUsers.put(username, professor);
            currentUser = professor;
            return professor;
        }
        return null;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}