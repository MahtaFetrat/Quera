package com.example.quera.view.assignments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quera.R;
import com.example.quera.model.Answer;
import com.example.quera.model.Assignment;
import com.example.quera.model.Student;
import com.example.quera.model.Class;

import java.util.ArrayList;

public class StudentAssignmentsFragment extends AppCompatActivity {
    protected Class enteredClass;
    protected ArrayList<Assignment> classAssignments;
    protected ArrayList<Answer> answers;
    protected ArrayList<String> assignmentsName, studentAnswers;
    protected ArrayList<Float> studentGrades;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_student_assignments);

        classAssignments = enteredClass.getAssignments();
        answers = Student.getLoggedStudent().getAnswers();
        int i = 0;
        for (Assignment assignment : classAssignments) {
            assignmentsName.add(assignment.getName());
            if (answers.get(i).getAssignment().equals(assignment)) {
                studentAnswers.add(answers.get(i).getAnswer());
                studentGrades.add(answers.get(i).getGrade());
                i++;
            } else {
                studentAnswers.add("");
                studentGrades.add(0f);
            }
        }

        StudentAssignmentsAdapter studentAssignmentsAdapter = new StudentAssignmentsAdapter(this, assignmentsName.toArray(), studentAnswers.toArray(), studentGrades.toArray());
        recyclerView.setAdapter(studentAssignmentsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
