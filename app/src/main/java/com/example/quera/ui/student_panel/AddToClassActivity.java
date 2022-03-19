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
import com.example.quera.controller.StudentPanelController;
import com.example.quera.model.Course;
import com.example.quera.model.Student;

public class AddToClassActivity extends BaseActivity {
    StudentPanelController controller = MainActivity.studentPanelController;

    TextView usernameTextView;
    TextView classesToAddTextView;
    Button confirmButton;
    EditText classNameEditText;
    Student student;
    TextView messageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_class);

        usernameTextView = findViewById(R.id.usernameAddToClassTextView);
        classesToAddTextView = findViewById(R.id.allClassseToAddTextVeiw);
        classNameEditText = findViewById(R.id.classNameAddToClass);
        messageTextView = findViewById(R.id.addToClassMessage);
        confirmButton = findViewById(R.id.addToClassConfirmButton);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        student = controller.getStudentByUsername(username);

        usernameTextView.setText(username);
        classesToAddTextView.setText(controller.getClassNamesStudentCanJoin(student));

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Course c = controller.getStudentClassByName(student, classNameEditText.getText().toString());
                if (!(controller.getClassNamesStudentCanJoin(student).contains(c.getName()))) {
                    messageTextView.setTextColor(Color.RED);
                } else {
                    controller.addStudentToClass(student, c);
                }
            }
        });
    }
}
