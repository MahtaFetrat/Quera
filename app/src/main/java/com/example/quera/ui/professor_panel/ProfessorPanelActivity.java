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
import com.example.queratest.module.Class;
import com.example.queratest.module.Professor;
import com.example.queratest.ui.student_panel.StudentClassActivity;
import com.example.queratest.ui.student_panel.StudentPanelActivity;

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

        professor = new Professor("username", "12345678", "amir", "sharif");

        nameTextView = findViewById(R.id.usernameProfessorPanelTextView);
        classesTextView = findViewById(R.id.professorClassesTextView);
        createClassButton = findViewById(R.id.createClass);
        classNameEditText = findViewById(R.id.classNameProfessorPanel);
        confirmButton = findViewById(R.id.proffesorPanelConfirmClassButton);
        messageTextView = findViewById(R.id.professorPanelMessage);


        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        this.professor = controller.getProfessorByUsername(username);
        nameTextView.setText(username);
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