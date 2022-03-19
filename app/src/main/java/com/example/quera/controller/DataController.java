package com.example.quera.controller;

import com.example.quera.model.Assignment;
import com.example.quera.model.Professor;
import com.example.quera.model.Student;
import com.example.quera.model.User;
import com.example.quera.model.Course;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

public class DataController {
    private static User currentUser;
    private static Gson gson;

    public static User login(String username, String password) {
        User user = User.getAllUsers().get(username);
        if (user != null && user.passwordMatches(password)) {
            currentUser = user;
            return user;
        }
        return null;
    }

    public static void logout() {
        currentUser = null;
    }

    public static Student signupStudent(String username, String password, String firstname, String lastname, String studentNumber) {
        Student student = new Student(username, password, firstname, lastname, studentNumber);
        if (!User.isUsernameAvailable(username)){
            Student.getAllStudents().put(username, student);
            currentUser = student;
            return student;
        }
        return null;
    }

    public static Professor signupInstructor(String username, String password, String firstname, String lastname, String universityName) {
        Professor professor = new Professor(username, password, firstname, lastname, universityName);
        if (!User.isUsernameAvailable(username)){
            Professor.getAllProfessors().put(username, professor);
            currentUser = professor;
            return professor;
        }
        return null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static String getStudentsDataString() {
        return new Gson().toJson(Student.getAllStudents());
    }

    public static String getProfessorsDataString() {
        return new Gson().toJson(Professor.getAllProfessors());
    }

    public static String getClassesDataString() {
        return new Gson().toJson(Course.getAllClasses());
    }

    public static String getAssignmentsDataString() {
        return new Gson().toJson(Assignment.getAllAssignments());
    }

    public static void readStudentsDataString(String dataString) {
        Type type = new TypeToken<HashMap<String, Student>>(){}.getType();
        Student.setAllStudents(new Gson().fromJson(dataString, type));
        // TODO: read Class objects by some key
    }

    public static void readProfessorsDataString(String dataString) {
        Type type = new TypeToken<HashMap<String, Professor>>() {}.getType();
        Professor.setAllProfessors(new Gson().fromJson(dataString, type));
        // TODO: read Class objects by some key
    }

    public static void readClassesDataString(String dataString) {
        Type type = new TypeToken<HashMap<String, Course>>() {}.getType();
        Course.setAllClasses(new Gson().fromJson(dataString, type));
        // TODO: read Class objects by some key
    }

    public static void readAssignmentsDataString(String dataString) {
        Type type = new TypeToken<HashMap<String, Assignment>>() {}.getType();
        Assignment.setAllAssignments(new Gson().fromJson(dataString, type));
        // TODO: read Class objects by some key
    }
}