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
import com.example.queratest.controll.ClassController;
import com.example.queratest.module.Class;
import com.example.queratest.module.Professor;
import com.example.queratest.module.Student;

public class ProfessorClassActivity extends AppCompatActivity {
    ClassController controller = MainActivity.classController;

    Professor professor;
    Class clas;
    TextView classNameTextView;
    TextView professorNameTextView;
    TextView assignmentsTextView;
    EditText assignmentEditText;
    Button confirmButton;
    TextView messageTextView;
    Button createAssignmentButton;

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
        createAssignmentButton = findViewById(R.id.professorClassCreateAssignment);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String className = intent.getStringExtra("className");

        this.professor = MainActivity.professorPanelController.getProfessorByUsername(username);
        this.clas = controller.getUserClassByName(professor, className);
        classNameTextView.setText(className);
        professorNameTextView.setText(professor.getName());
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

        createAssignmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* TODO go to create assignment page
                Intent intent = new Intent(...)
                 */
            }
        });
    }
}
