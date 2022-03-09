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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.quera.MainActivity;
import com.example.quera.R;
import com.example.quera.utils.Validator;
import com.google.android.material.textfield.TextInputEditText;


public class StudentSignupFragment extends Fragment {

    private TextInputEditText usernameField;
    private TextInputEditText firstName;
    private TextInputEditText lastName;
    private TextInputEditText studentNumber;
    private TextInputEditText passwordField;
    private TextInputEditText passwordRepeatField;
    private Button signupButton;
    private LinearLayout layout;

    private boolean isFormValid = false;

    public StudentSignupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = (LinearLayout) inflater.inflate(R.layout.fragment_student_signup, container, false);

        findViews();
        setFromValidators();
        setSignupButtonOnClick();

        return layout;
    }

    private void findViews() {
        usernameField = layout.findViewById(R.id.signupStudentUsernameField);
        firstName = layout.findViewById(R.id.signupStudentFirstNameField);
        lastName = layout.findViewById(R.id.signupStudentLastNameField);
        studentNumber = layout.findViewById(R.id.signupStudentNumberField);
        passwordField = layout.findViewById(R.id.signupStudentPasswordField);
        passwordRepeatField = layout.findViewById(R.id.signupStudentPasswordRepeatField);
        signupButton = layout.findViewById(R.id.studentSignupButton);
    }

    private void setFromValidators() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                isFormValid = false;
                if (MainActivity.dataHandler.isUsernameAvailable(usernameField.getText().toString())
                        && !TextUtils.isEmpty(usernameField.getText())) {
                    usernameField.setError(getString(R.string.username_taken_error));
                } else if (Validator.isPasswordInvalid(passwordField.getText().toString())
                        && !TextUtils.isEmpty(passwordField.getText())) {
                    passwordField.setError(getString(R.string.invalid_password_error));
                } else if (Validator.passwordsMismatch(passwordField.getText().toString(), passwordRepeatField.getText().toString())
                        && !TextUtils.isEmpty(passwordRepeatField.getText())) {
                    passwordRepeatField.setError(getString(R.string.password_repeat_error));
                } else {
                    isFormValid = true;
                }
            }
        };
        usernameField.addTextChangedListener(textWatcher);
        passwordField.addTextChangedListener(textWatcher);
        passwordRepeatField.addTextChangedListener(textWatcher);
    }

    private boolean hasEmptyRequiredFields() {
        return  TextUtils.isEmpty(usernameField.getText()) ||
                TextUtils.isEmpty(firstName.getText()) ||
                TextUtils.isEmpty(lastName.getText()) ||
                TextUtils.isEmpty(studentNumber.getText()) ||
                TextUtils.isEmpty(passwordField.getText()) ||
                TextUtils.isEmpty(passwordRepeatField.getText());
    }

    private void setSignupButtonOnClick() {
        signupButton.setOnClickListener(view -> {
            if (!isFormValid) {
                Toast.makeText(getActivity(), R.string.fix_errors, Toast.LENGTH_SHORT).show();
            } else if (hasEmptyRequiredFields()) {
                Toast.makeText(getActivity(), R.string.required_error, Toast.LENGTH_SHORT).show();
            } else {
                // student signup stuff and redirection
            }
        });
    }
}