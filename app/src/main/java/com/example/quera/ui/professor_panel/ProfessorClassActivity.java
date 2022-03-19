package com.example.quera.ui.professor_panel;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quera.BaseActivity;
import com.example.quera.MainActivity;
import com.example.quera.R;
import com.example.quera.controller.ClassController;
import com.example.quera.model.Course;
import com.example.quera.model.Professor;

public class ProfessorClassActivity extends BaseActivity {
    ClassController controller = MainActivity.classController;

    Professor professor;
    Course clas;
    TextView classNameTextView;
    TextView professorNameTextView;
    TextView assignmentsTextView;
    EditText assignmentEditText;
    TextView messageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_class);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String className = intent.getStringExtra("className");

        classNameTextView = findViewById(R.id.classNameStudentClassTextView);
        professorNameTextView = findViewById(R.id.professorNameStudentClassTextView);
        assignmentsTextView = findViewById(R.id.studentClassAssignmentsTextView);
        assignmentEditText = findViewById(R.id.assignmentNameStudentClass);
        messageTextView = findViewById(R.id.studentClassMessage);

        this.professor = MainActivity.professorPanelController.getProfessorByUsername(username);
        this.clas = controller.getUserClassByName(professor, className);
        classNameTextView.setText(className);
        professorNameTextView.setText(professor.getName());
        assignmentsTextView.setText(controller.getClassAssignments(this.clas));
    }

    public void navToAssignment(View view) {
        if (controller.getClassAssignmentByName(clas, assignmentEditText.getText().toString()) == null) {
            messageTextView.setTextColor(Color.RED);
        } else {
                    /* TODO go to assignment page
                    Intent intent = new Intent(...)
                    ...
                     */
        }
    }

    public void createAssignment(View view) {
        /* TODO go to create assignment page
                Intent intent = new Intent(...)
                 */
    }
}
