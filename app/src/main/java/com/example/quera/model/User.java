package com.example.quera.model;

import java.util.ArrayList;

public class User {
    protected String username;
    protected String password;
    protected String firstname;
    protected String lastname;
    protected ArrayList<Class> classes;

    public User(String username, String password, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Class getClassByName(String name) {
        for (Class c : this.classes) {
            if (name.equals(c.name))
                return c;
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public void addStudentToClass(Class c){
        this.classes.add(c);
    }

    public boolean passwordMatches(String password) {
        return this.password.equals(password);
    }
}
