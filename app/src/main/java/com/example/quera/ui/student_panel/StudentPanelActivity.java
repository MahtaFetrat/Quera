package com.example.quera.ui.student_panel;

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
import com.example.quera.controller.DataController;
import com.example.quera.controller.StudentPanelController;
import com.example.quera.model.Student;
import com.example.quera.model.Course;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StudentPanelActivity extends BaseActivity {

    StudentPanelController controller = MainActivity.studentPanelController;

    Student student;
    TextView nameTextView;
    TextView classesTextView;
    EditText classNameEditText;
    Button confirmButton;
    FloatingActionButton addToClassButton;
    TextView messageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_panel);

        nameTextView = findViewById(R.id.usernameStudentPanelTextView);
        classesTextView = findViewById(R.id.studentClassesTextView);
        addToClassButton = findViewById(R.id.addToClassButton);
        confirmButton = findViewById(R.id.studentPanelConfirmClassButton);
        classNameEditText = findViewById(R.id.classNameStudentPanel);
        messageTextView = findViewById(R.id.studentPanelMessage);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Course c;
                if ((c = controller.getStudentClassByName(student, classNameEditText.getText().toString())) == null) {
                    messageTextView.setTextColor(Color.RED);
                } else {
                    Intent intent = new Intent(StudentPanelActivity.this, StudentClassActivity.class);
                    intent.putExtra("username", student.getUsername());
                    intent.putExtra("className", c.getName());
                    startActivity(intent);
                }
            }
        });

        addToClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentPanelActivity.this, AddToClassActivity.class);
                intent.putExtra("username", student.getUsername());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        student = (Student) DataController.getCurrentUser();
        nameTextView.setText(student.getUsername());
        classesTextView.setText(controller.getStudentClassNames(this.student));
    }
}