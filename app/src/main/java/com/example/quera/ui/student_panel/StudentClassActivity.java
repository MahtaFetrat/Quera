package com.example.quera.ui.student_panel;

import android.content.Intent;
import android.os.Bundle;

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

import java.util.ArrayList;

public class StudentClassActivity extends BaseActivity {
    StudentPanelController studentController = MainActivity.studentPanelController;
    ClassController classController = MainActivity.classController;

    protected Course course;
    protected ArrayList<Assignment> classAssignments;
    protected ArrayList<Answer> answers;
    protected ArrayList<String> assignmentsName, studentAnswers = new ArrayList<>();
    protected ArrayList<Float> studentGrades = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_student_assignments);

        RecyclerView recyclerView = findViewById(R.id.assignmentList);

        Intent intent = getIntent();
        course = classController.getClassByName(intent.getStringExtra("className"));
        classAssignments = course.getAssignments();
        assignmentsName = course.getAssignmentIds();
        answers = studentController.getStudentByUsername(intent.getStringExtra("username")).getAnswers();
        int i = 0;
        for (Assignment assignment : classAssignments) {
            if (answers.size() > 0 && answers.get(i).getAssignment().equals(assignment)) {
                studentAnswers.add(answers.get(i).getAnswer());
                studentGrades.add(answers.get(i).getGrade());
                i++;
            } else {
                studentAnswers.add("");
                studentGrades.add(0f);
            }
        }

        StudentAssignmentsAdapter studentAssignmentsAdapter = new StudentAssignmentsAdapter(this, assignmentsName.toArray(new String[0]), studentAnswers.toArray(new String[0]), studentGrades.toArray(new Float[0]), intent.getStringExtra("username"), course.getName());
        recyclerView.setAdapter(studentAssignmentsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
