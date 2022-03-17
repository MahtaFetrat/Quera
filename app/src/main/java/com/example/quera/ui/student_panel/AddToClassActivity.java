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
import com.example.queratest.module.Class;
import com.example.queratest.module.Student;

public class AddToClassActivity extends AppCompatActivity {
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

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        student = controller.getStudentByUsername(username);

        usernameTextView.setText(username);
        classesToAddTextView.setText(controller.getClassNamesStudentCanJoin(student));

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class c = controller.getStudentClassByName(student, classNameEditText.getText().toString());
                if (!(controller.getClassNamesStudentCanJoin(student).contains(c.getName()))) {
                    messageTextView.setTextColor(Color.RED);
                } else {
                    controller.addStudentToClass(student, c);
                }
            }
        });
    }
}
