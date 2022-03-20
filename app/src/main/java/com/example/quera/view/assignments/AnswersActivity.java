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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_answers);

        RecyclerView recyclerView = findViewById(R.id.answerList);
        Assignment assignment = Assignment.getAssignmentById(getIntent().getStringExtra("assignmentId"));
        ArrayList<Answer> answers = assignment.getAnswers();

        AnswersAdapter answersAdapter = new AnswersAdapter(this, answers.toArray(new Answer[0]));
        recyclerView.setAdapter(answersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
