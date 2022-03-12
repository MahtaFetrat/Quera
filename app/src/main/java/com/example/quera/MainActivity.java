package com.example.quera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.quera.controller.DataController;
import com.example.quera.view.login_signup.LoginActivity;

public class MainActivity extends AppCompatActivity {
    public static DataController dataController = new DataController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readData();

        /*TODO: To be replaced with conditional navigation to dashboard/login fragment based on sign in information loaded at startup
           read https://developer.android.com/guide/navigation/navigation-conditional for more*/
        if (dataController.getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    private void writeData() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(getString(R.string.saved_users_key), dataController.getUsersDataString());

        editor.apply();
    }

    private void readData() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        String usersDataString = sharedPref.getString(getString(R.string.saved_users_key), dataController.getUsersDataString());
        dataController.readUsersDataString(usersDataString);
    }

    @Override
    protected void onStop() {
        // call the superclass method first
        super.onStop();
        writeData();
    }
}