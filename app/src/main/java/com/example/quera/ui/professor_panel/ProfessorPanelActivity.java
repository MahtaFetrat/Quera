package com.example.quera.ui.professor_panel;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quera.MainActivity;
import com.example.quera.R;
import com.example.quera.controller.ProfessorPanelController;
import com.example.quera.model.Class;
import com.example.quera.model.Professor;
import com.example.quera.ui.student_panel.StudentClassActivity;
import com.example.quera.ui.student_panel.StudentPanelActivity;
import com.example.quera.ui.professor_panel.CreateClassActivity;

public class ProfessorPanelActivity extends AppCompatActivity {

    ProfessorPanelController controller = MainActivity.professorPanelController;

    Professor professor;
    TextView nameTextView;
    TextView classesTextView;
    EditText classNameEditText;
    Button createClassButton;
    Button confirmButton;
    TextView messageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_panel);

        professor = new Professor("username", "12345678", "amir", "amiri", "sharif");

        nameTextView = findViewById(R.id.usernameProfessorPanelTextView);
        classesTextView = findViewById(R.id.professorClassesTextView);
        createClassButton = findViewById(R.id.createClass);
        classNameEditText = findViewById(R.id.classNameProfessorPanel);
        confirmButton = findViewById(R.id.proffesorPanelConfirmClassButton);
        messageTextView = findViewById(R.id.professorPanelMessage);

        professor = (Professor) MainActivity.dataController.getCurrentUser();
        nameTextView.setText(professor.getUsername());
        classesTextView.setText(controller.getProfessorClassNames(professor));

        createClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfessorPanelActivity.this, CreateClassActivity.class);
                intent.putExtra("username", professor.getUsername());
                startActivity(intent);
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class c;
                if ((c = controller.getProfessorClassByName(professor, classNameEditText.getText().toString())) == null) {
                    messageTextView.setTextColor(Color.RED);
                } else {
                    Intent intent = new Intent(ProfessorPanelActivity.this, StudentClassActivity.class);
                    intent.putExtra("username", professor.getUsername());
                    intent.putExtra("className", c.getName());
                    startActivity(intent);
                }
            }
        });
    }
}