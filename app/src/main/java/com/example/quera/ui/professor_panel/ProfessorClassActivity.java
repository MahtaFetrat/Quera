package com.example.quera.ui.professor_panel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quera.BaseActivity;
import com.example.quera.MainActivity;
import com.example.quera.R;
import com.example.quera.controller.ClassController;
import com.example.quera.model.Assignment;
import com.example.quera.model.Course;
import com.example.quera.model.Professor;

import java.util.ArrayList;

public class ProfessorClassActivity extends BaseActivity {
    ClassController controller = MainActivity.classController;

    protected Course course;
    protected Professor professor;
    protected ArrayList<String> assignmentsName = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_professor_class);

        RecyclerView recyclerView = findViewById(R.id.ProfessorAssignmentsRecyclerView);
        TextView classNameTextView = findViewById(R.id.professorClassActivityClassName);
        TextView professorNameTextView = findViewById(R.id.professorClassActivityProfessorName);


        Intent intent = getIntent();
        course = controller.getClassByName(intent.getStringExtra("className"));
        assignmentsName = course.getAssignmentIds();

        if (assignmentsName.size() != 0) {
            ProfessorAssignmentsAdapter professorAssignmentsAdapter = new ProfessorAssignmentsAdapter(this, assignmentsName.toArray(new String[0]));
            recyclerView.setAdapter(professorAssignmentsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        professor = MainActivity.professorPanelController.getProfessorByUsername(intent.getStringExtra("username"));
        professorNameTextView.setText(professorNameTextView.getText().toString() + " " + professor.getName());
        classNameTextView.setText(classNameTextView.getText().toString() + " " + course.getName());
    }

    public void addAssignment(View view) {
        AlertDialog.Builder inputAssignmentName = new AlertDialog.Builder(this);

        inputAssignmentName.setTitle("Create new assignment");
        inputAssignmentName.setMessage("Enter Assignment title");

        final EditText nameInputField = new EditText(this);
        inputAssignmentName.setView(nameInputField);

        inputAssignmentName.setPositiveButton("Create", (dialog, whichButton) -> {
            String name = nameInputField.getText().toString();
            new Assignment(name, course.getName());
            finish();
            startActivity(getIntent());
        });

        inputAssignmentName.setNegativeButton("Cancel", (dialog, whichButton) -> {
        });

        inputAssignmentName.show();
    }
}
