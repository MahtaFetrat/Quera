package com.example.quera.utils;

public final class Validator {
    public static boolean isPasswordInvalid(String password) {
        return password.length() < 8;
    }

    public static boolean passwordsMismatch(String password, String passwordRepeat) {
        return !password.equals(passwordRepeat);
    }
}
