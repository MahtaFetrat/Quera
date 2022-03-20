package com.example.quera.view.assignments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quera.R;
import com.example.quera.model.Answer;

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.AnswersViewHolder> {
    protected String[] answers;
    protected float[] grades;
    protected Context context;

    public AnswersAdapter(AnswersActivity ct, String[] studentAnswers, String[] studentGrades) {
        context = ct;
        answers = studentAnswers;
        grades = new float[studentGrades.length];
        for (int i = 0; i < studentGrades.length; i++) {
            grades[i] = Float.parseFloat(studentGrades[i].toString());
        }
    }

    @NonNull
    @Override
    public AnswersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.answer_row, parent, false);

        return new AnswersViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AnswersViewHolder holder, int position) {
        holder.answerText.setText(answers[position]);
        holder.gradeText.setText(Float.toString(grades[position]));
    }

    @Override
    public int getItemCount() {
        return answers.length;
    }

    public static class AnswersViewHolder extends RecyclerView.ViewHolder {
        protected TextView answerText;
        protected EditText gradeText;
        protected Button gradeButton;

        @SuppressLint("SetTextI18n")
        public AnswersViewHolder(@NonNull View itemView) {
            super(itemView);

            answerText = itemView.findViewById(R.id.answerButtonID);
            gradeText = itemView.findViewById(R.id.gradeID);
            gradeButton = itemView.findViewById(R.id.setGradeID);

            gradeButton.setOnClickListener(view -> {
                gradeText.setEnabled(!gradeText.isEnabled());
                if (gradeText.isEnabled()) {
                    gradeButton.setText("OK");
                } else {
                    gradeButton.setText("Grade");
                    float grade = Float.parseFloat(gradeText.getText().toString());
//                    Answer.getCurrentAnswer().setGrade(grade);
                }
            });
        }
    }
}
