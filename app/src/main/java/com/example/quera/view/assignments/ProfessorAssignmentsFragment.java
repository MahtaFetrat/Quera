package com.example.quera.view.assignments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quera.R;
import com.example.quera.controller.ClassController;
import com.example.quera.model.Assignment;
import com.example.quera.model.Course;

import java.util.ArrayList;

public class ProfessorAssignmentsFragment extends AppCompatActivity {
    protected Course course;
    protected ArrayList<Assignment> classAssignments;
    protected ArrayList<String> assignmentsName;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_professor_assignments);

        Intent intent = getIntent();

        course = ClassController.getClassByName(intent.getStringExtra("className"));
        classAssignments = course.getAssignments();

        for (Assignment assignment : classAssignments) {
            assignmentsName.add(assignment.getName());
        }

        ProfessorAssignmentsAdapter professorAssignmentsAdapter = new ProfessorAssignmentsAdapter(this, assignmentsName.toArray());
        recyclerView.setAdapter(professorAssignmentsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void addAssignment(View view) {
        // do something...
    }
}
