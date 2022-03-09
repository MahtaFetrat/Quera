package com.example.quera.ui.login_signup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;

import com.example.quera.MainActivity;
import com.example.quera.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginFragment extends Fragment {
    private TextInputEditText usernameField;
    private TextInputEditText passwordField;
    private Button loginButton;
    private ScrollView view;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.login_fragment_label);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = (ScrollView) inflater.inflate(R.layout.fragment_login, container, false);

        findViews();
        setFromValidator();
        setLoginButtonOnClick();

        return view;
    }

    private void findViews() {
        usernameField = view.findViewById(R.id.loginUsernameField);
        passwordField = view.findViewById(R.id.loginPasswordField);
        loginButton = view.findViewById(R.id.loginButton);
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

    private void setLoginButtonOnClick() {
        loginButton.setOnClickListener(view -> {
            // instructor signup stuff and redirection
        });
    }
}