package com.example.quera.controller;

import android.util.Log;

import com.example.quera.model.Professor;
import com.example.quera.model.Student;
import com.example.quera.model.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class DataController {
    private User currentUser;
    private Gson gson;

    public User login(String username, String password) {
        User user = User.getAllUsers().get(username);
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
        if (!User.isUsernameAvailable(username)){
            Student.getAllStudents().put(username, student);
            currentUser = student;
            return student;
        }
        return null;
    }

    public Professor signupInstructor(String username, String password, String firstname, String lastname, String universityName) {
        Professor professor = new Professor(username, password, firstname, lastname, universityName);
        if (!User.isUsernameAvailable(username)){
            Professor.getAllProfessors().put(username, professor);
            currentUser = professor;
            return professor;
        }
        return null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public String getStudentsDataString() {
        return new Gson().toJson(Student.getAllStudents());
    }

    public String getProfessorsDataString() {
        return new Gson().toJson(Professor.getAllProfessors());
    }

    public void readStudentsDataString(String dataString) {
        Type type = new TypeToken<HashMap<String, Student>>(){}.getType();
        Student.setAllStudents(new Gson().fromJson(dataString, type));
        // TODO: read Class objects by some key
    }

    public void readProfessorsDataString(String dataString) {
        Type type = new TypeToken<HashMap<String, Professor>>(){}.getType();
        Professor.setAllProfessors(new Gson().fromJson(dataString, type));
        // TODO: read Class objects by some key
    }
}