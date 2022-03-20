package com.example.quera.view.assignments;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quera.BaseActivity;
import com.example.quera.R;
import com.example.quera.model.Answer;
import com.example.quera.model.Assignment;

import java.util.ArrayList;

public class AnswersActivity extends BaseActivity {
    protected Assignment enteredAssignment;
    protected ArrayList<Answer> classAnswer = new ArrayList<>();
    protected ArrayList<String> answers = new ArrayList<>();
    protected ArrayList<Float> grades = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_answers);

        recyclerView = findViewById(R.id.answerList);
        enteredAssignment = Assignment.getAssignmentById(getIntent().getStringExtra("enteredAssignmentId"));
        classAnswer = enteredAssignment.getAnswers();

        for (Answer answer : classAnswer) {
            answers.add(answer.getAnswer());
            grades.add(answer.getGrade());
        }

        AnswersAdapter answersAdapter = new AnswersAdapter(this, answers.toArray(new String[0]), grades.toArray(new String[0]));
        recyclerView.setAdapter(answersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
