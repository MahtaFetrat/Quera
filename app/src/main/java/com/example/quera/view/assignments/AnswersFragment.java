package com.example.quera.view.assignments;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quera.BaseActivity;
import com.example.quera.R;
import com.example.quera.model.Answer;
import com.example.quera.model.Assignment;

import java.util.ArrayList;

public class AnswersFragment extends BaseActivity {
    protected Assignment enteredAssignment;
    protected ArrayList<Answer> classAnswer;
    protected ArrayList<String> answers;
    protected ArrayList<Float> grades;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_answers);

        classAnswer = enteredAssignment.getAnswers();

        for (Answer answer : classAnswer) {
            answers.add(answer.getAnswer());
            grades.add(answer.getGrade());
        }

        AnswersAdapter answersAdapter = new AnswersAdapter(this, answers.toArray(), grades.toArray());
        recyclerView.setAdapter(answersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
