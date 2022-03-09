package com.example.quera.model;

import java.util.ArrayList;

public class User {
    protected String username;
    protected String password;
    protected String firstname;
    protected String lastname;
    protected ArrayList<Class> Classes;

    public User(String username, String password, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public boolean passwordMatches(String password) {
        return password.equals(password);

    }
}
