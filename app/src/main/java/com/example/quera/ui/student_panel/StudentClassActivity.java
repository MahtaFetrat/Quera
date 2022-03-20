package com.example.quera.ui.student_panel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quera.BaseActivity;
import com.example.quera.controller.ClassController;
import com.example.quera.MainActivity;
import com.example.quera.R;
import com.example.quera.controller.StudentPanelController;
import com.example.quera.model.Answer;
import com.example.quera.model.Assignment;
import com.example.quera.model.Course;
import com.example.quera.model.Student;

import java.util.ArrayList;

public class StudentClassActivity extends BaseActivity {
    ClassController classController = MainActivity.classController;

    protected ArrayList<String> assignmentIds = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_student_class);

        TextView professorName = findViewById(R.id.studentClassActivityProfessorName);
        TextView className = findViewById(R.id.studentClassActivityClassName);
        RecyclerView recyclerView = findViewById(R.id.studentAssignmentsListView);

        Intent intent = getIntent();
        Course course = classController.getClassByName(intent.getStringExtra("className"));
        Student student = MainActivity.studentPanelController.getStudentByUsername(intent.getStringExtra("username"));
        assignmentIds = course.getAssignmentIds();

        StudentAssignmentsAdapter studentAssignmentsAdapter = new StudentAssignmentsAdapter(this, assignmentIds.toArray(new String[0]), student, course);
        recyclerView.setAdapter(studentAssignmentsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        professorName.setText(professorName.getText().toString() + " " + course.getProfessor().getName());
        className.setText(className.getText().toString() + " " + course.getName());
    }
}
