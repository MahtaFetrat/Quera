package com.example.quera.view.login_signup;

import android.app.Activity;
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

import com.example.quera.R;
import com.example.quera.controller.DataController;
import com.example.quera.model.Professor;
import com.example.quera.model.User;
import com.example.quera.utils.Validator;
import com.google.android.material.textfield.TextInputEditText;

public class InstructorSignupFragment extends Fragment {

    private TextInputEditText usernameField;
    private TextInputEditText firstNameField;
    private TextInputEditText lastNameField;
    private TextInputEditText universityNameField;
    private TextInputEditText passwordField;
    private TextInputEditText passwordRepeatField;
    private Button signupButton;
    private LinearLayout layout;

    private boolean isFormValid = false;

    public InstructorSignupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = (LinearLayout) inflater.inflate(R.layout.fragment_instructor_signup, container, false);

        findViews();
        setFromValidators();
        setSignupButtonOnClick();

        return layout;
    }

    private void findViews() {
        usernameField = layout.findViewById(R.id.signupInstructorUsernameField);
        firstNameField = layout.findViewById(R.id.signupInstructorFirstNameField);
        lastNameField = layout.findViewById(R.id.signupInstructorLastNameField);
        universityNameField = layout.findViewById(R.id.signupInstructorUniversityNameField);
        passwordField = layout.findViewById(R.id.signupInstructorPasswordField);
        passwordRepeatField = layout.findViewById(R.id.signupInstructorPasswordRepeatField);
        signupButton = layout.findViewById(R.id.instructorSignupButton);
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
                if (User.isUsernameAvailable(usernameField.getText().toString())
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
                TextUtils.isEmpty(firstNameField.getText()) ||
                TextUtils.isEmpty(lastNameField.getText()) ||
                TextUtils.isEmpty(universityNameField.getText()) ||
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
                Professor professor = DataController.signupInstructor(usernameField.getText().toString(),
                        passwordField.getText().toString(), firstNameField.getText().toString(),
                        lastNameField.getText().toString(), universityNameField.getText().toString());
                if (professor != null) {
                    Toast.makeText(getActivity(), R.string.successful_signup, Toast.LENGTH_SHORT).show();
                    getActivity().setResult(Activity.RESULT_OK);
                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(), R.string.error_signing_up, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}