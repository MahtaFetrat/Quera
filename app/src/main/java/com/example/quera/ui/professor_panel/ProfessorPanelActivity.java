package com.example.quera.ui.professor_panel;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.quera.BaseActivity;
import com.example.quera.MainActivity;
import com.example.quera.R;
import com.example.quera.controller.DataController;
import com.example.quera.controller.ProfessorPanelController;
import com.example.quera.model.Course;
import com.example.quera.model.Professor;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class ProfessorPanelActivity extends BaseActivity {

    ProfessorPanelController controller = MainActivity.professorPanelController;

    Professor professor;
    TextView nameTextView;
    TextView classesTextView;
    TextInputEditText classNameEditText;
    FloatingActionButton createClassButton;
    Button confirmButton;
    TextView messageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_panel);

        nameTextView = findViewById(R.id.usernameProfessorPanelTextView);
        classesTextView = findViewById(R.id.professorClassesTextView);
        createClassButton = findViewById(R.id.createClass);
        classNameEditText = findViewById(R.id.classNameProfessorPanel);
        confirmButton = findViewById(R.id.professorEnterClassConfirm);
        messageTextView = findViewById(R.id.professorPanelMessage);

        createClassButton.setOnClickListener(view -> {
            Intent intent = new Intent(ProfessorPanelActivity.this, CreateClassActivity.class);
            intent.putExtra("username", professor.getUsername());
            startActivity(intent);
        });

        confirmButton.setOnClickListener(view -> {
            Course c = controller.getProfessorClassByName(professor, classNameEditText.getText().toString());
            if (c == null) {
                messageTextView.setTextColor(Color.RED);
            } else if (!professor.getClassNames().contains(c.getName())) {
                classNameEditText.setError(getString(R.string.not_the_class_owner));
            } else {
                Intent intent = new Intent(ProfessorPanelActivity.this, ProfessorClassActivity.class);
                intent.putExtra("username", professor.getUsername());
                intent.putExtra("className", c.getName());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        professor = (Professor) DataController.getCurrentUser();
        nameTextView.setText(professor.getFullName());
        classesTextView.setText(controller.getProfessorClassNames(professor));
    }
}