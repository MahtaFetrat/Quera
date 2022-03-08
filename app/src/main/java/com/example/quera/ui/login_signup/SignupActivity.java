package com.example.quera.ui.login_signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.quera.MainActivity;
import com.example.quera.R;
import com.google.android.material.tabs.TabLayout;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setDefaultTab();
        setTabLayout();
    }

    private void setDefaultTab() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.signupTabFragmentContainer, new StudentSignupFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    private void setTabLayout() {
        ((TabLayout) findViewById(R.id.signupTabLayout)).addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = new StudentSignupFragment();
                if (tab.getPosition() == 1) {
                    fragment = new InstructorSignupFragment();
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.signupTabFragmentContainer, fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    public void navToLoginForm(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}