package com.example.quera.ui.student_panel;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quera.BaseActivity;
import com.example.quera.controller.ClassController;
import com.example.quera.MainActivity;
import com.example.quera.R;
import com.example.quera.model.Course;
import com.example.quera.model.Student;

public class StudentClassActivity extends BaseActivity {
    ClassController controller = MainActivity.classController;

    Student student;
    Course clas;
    TextView classNameTextView;
    TextView professorNameTextView;
    TextView assignmentsTextView;
    EditText assignmentEditText;
    Button confirmButton;
    TextView messageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_class);

        classNameTextView = findViewById(R.id.classNameStudentClassTextView);
        professorNameTextView = findViewById(R.id.professorNameStudentClassTextView);
        assignmentsTextView = findViewById(R.id.studentClassAssignmentsTextView);
        confirmButton = findViewById(R.id.confirmClassButton);
        assignmentEditText = findViewById(R.id.assignmentNameStudentClass);
        messageTextView = findViewById(R.id.studentClassMessage);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String className = intent.getStringExtra("className");

        this.student = MainActivity.studentPanelController.getStudentByUsername(username);
        this.clas = controller.getUserClassByName(student, className);
        classNameTextView.setText(className);
        professorNameTextView.setText(clas.getProfessor().getName());
        assignmentsTextView.setText(controller.getClassAssignments(this.clas));

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (controller.getClassAssignmentByName(clas, assignmentEditText.getText().toString()) == null) {
                    messageTextView.setTextColor(Color.RED);
                } else {
                    /* TODO go to assignment page
                    Intent intent = new Intent(...)
                    ...
                     */
                }
            }
        });
    }
}
