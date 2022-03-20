package com.example.quera.model;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    protected String username;
    protected String password;
    protected String firstname;
    protected String lastname;
    protected ArrayList<String> classNames = new ArrayList<>();

    public User(String username, String password, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public static boolean isUsernameAvailable(String username) {
        return Student.getAllStudents().containsKey(username) || Professor.getAllProfessors().containsKey(username);
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return firstname + " " + lastname;
    }
    
    public ArrayList<String> getClassNames() {
        return classNames;
    }

    public void addStudentToClass(String classId){
        this.classNames.add(classId);
    }

    public boolean passwordMatches(String password) {
        return this.password.equals(password);
    }

    public static HashMap<String, User> getAllUsers() {
        HashMap<String, User> allUsers = new HashMap<>();
        allUsers.putAll(Student.getAllStudents());
        allUsers.putAll(Professor.getAllProfessors());
        return allUsers;
    }

}
