package com.example.quera.controller;

import com.example.quera.model.Professor;
import com.example.quera.model.Student;
import com.example.quera.model.User;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

public class DataController {
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

    public String getUsersDataString() {
        return new Gson().toJson(allUsers);
    }

    public void readUsersDataString(String dataString) {
        Type type = new TypeToken<HashMap<String, User>>(){}.getType();
        allUsers = new Gson().fromJson(dataString, type);
        // TODO: read Class objects by some key
    }
}