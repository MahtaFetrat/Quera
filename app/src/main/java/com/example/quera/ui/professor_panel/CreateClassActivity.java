package com.example.queratest.ui.professor_panel;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.queratest.MainActivity;
import com.example.queratest.R;
import com.example.queratest.controll.ProfessorPanelController;
import com.example.queratest.module.Professor;
import com.example.queratest.module.Class;

public class CreateClassActivity extends AppCompatActivity {
    ProfessorPanelController controller = MainActivity.professorPanelController;

    TextView usernameTextView;
    TextView nameIsUsedTextView;
    Button confirmButton;
    EditText classNameEditText;
    Professor professor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        usernameTextView = findViewById(R.id.usernameCreateClassTextView);
        nameIsUsedTextView = findViewById(R.id.createClassError);
        confirmButton = findViewById(R.id.confirmCreateClassButton);
        classNameEditText = findViewById(R.id.classNameCreateClass);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        professor = controller.getProfessorByUsername(username);

        usernameTextView.setText(username);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String className;
                if (MainActivity.classController.getClassByName((className = classNameEditText.getText().toString())) == null) {
                    nameIsUsedTextView.setTextColor(Color.RED);
                    nameIsUsedTextView.setVisibility(View.VISIBLE);
                } else {
                    controller.createClass(className, professor);
                }
            }
        });
    }
}
