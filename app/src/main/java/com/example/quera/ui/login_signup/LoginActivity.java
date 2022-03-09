package com.example.quera.ui.login_signup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.quera.MainActivity;
import com.example.quera.R;
import com.example.quera.model.User;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText usernameField;
    private TextInputEditText passwordField;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();
        setFromValidator();
    }

    private void findViews() {
        usernameField = findViewById(R.id.loginUsernameField);
        passwordField = findViewById(R.id.loginPasswordField);
        loginButton = findViewById(R.id.loginButton);
    }

    private void setFromValidator() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (!MainActivity.dataHandler.isUsernameAvailable(usernameField.getText().toString())) {
                    usernameField.setError(getString(R.string.invalid_username_error));
                    loginButton.setEnabled(false);
                } else {
                    loginButton.setEnabled(!hasEmptyRequiredFields());
                }
            }
        };
        usernameField.addTextChangedListener(textWatcher);
        passwordField.addTextChangedListener(textWatcher);
    }

    private boolean hasEmptyRequiredFields() {
        return TextUtils.isEmpty(usernameField.getText()) || TextUtils.isEmpty(passwordField.getText());
    }

    public void login(View view) {
        User user = MainActivity.dataHandler.login(usernameField.getText().toString(), passwordField.getText().toString());
        if (user != null) {
            Toast.makeText(this, R.string.successful_login, Toast.LENGTH_SHORT).show();
            setResult(Activity.RESULT_OK);
            finish();
        } else {
            Toast.makeText(this, R.string.error_loging_up, Toast.LENGTH_SHORT).show();
        }
    }

    public void navToSignupActivity(View view) {
        startActivity(new Intent(this, SignupActivity.class));
        finish();
    }
}