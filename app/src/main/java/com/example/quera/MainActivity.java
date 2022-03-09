package com.example.quera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.quera.controller.DataHandler;
import com.example.quera.ui.login_signup.LoginActivity;

public class MainActivity extends AppCompatActivity {
    public static DataHandler dataHandler = new DataHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*TODO: To be replaced with conditional navigation to dashboard/login fragment based on sign in information loaded at startup
           read https://developer.android.com/guide/navigation/navigation-conditional for more*/
        if (dataHandler.getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}