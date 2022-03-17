package com.example.queratest.ui.student_panel;

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
import com.example.queratest.controll.StudentPanelController;
import com.example.queratest.module.Student;
import com.example.queratest.module.Class;

public class StudentPanelActivity extends AppCompatActivity {

    StudentPanelController controller = MainActivity.studentPanelController;

    Student student;
    TextView nameTextView;
    TextView classesTextView;
    EditText classNameEditText;
    Button confirmButton;
    Button addToClassButton;
    TextView messageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_panel);

        nameTextView = findViewById(R.id.usernameStudentPanelTextView);
        classesTextView = findViewById(R.id.studentClassesTextView);
        addToClassButton = findViewById(R.id.addToClassButton);
        confirmButton = findViewById(R.id.confirmClassButton);
        classNameEditText = findViewById(R.id.classNameStudentPanel);
        messageTextView = findViewById(R.id.studentPanelMessage);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        this.student = controller.getStudentByUsername(username);
        nameTextView.setText(username);
        classesTextView.setText(controller.getStudentClassNames(this.student));

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class c;
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
}