package com.example.quera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.quera.ui.login_signup.LoginFragment;
import com.example.quera.ui.login_signup.SignupActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*TODO: To be replaced with conditional navigation to dashboard/login fragment based on sign in information loaded at startup
           read https://developer.android.com/guide/navigation/navigation-conditional for more*/
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_activity_fragment_layout, new LoginFragment())
                .commit();
    }

    public void navToSignupActivity(View view) {
        startActivity(new Intent(this, SignupActivity.class));
    }
}