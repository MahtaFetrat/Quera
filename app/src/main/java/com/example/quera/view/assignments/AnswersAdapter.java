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
import com.example.quera.model.Student;

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.AnswersViewHolder> {
    protected Answer[] answers;
    protected Context context;

    public AnswersAdapter(AnswersActivity ct, Answer[] studentAnswers) {
        context = ct;
        answers = studentAnswers;
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
        holder.answerText.setText(answers[position].getAnswer());
        holder.gradeText.setText(String.valueOf(answers[position].getGrade()));
        holder.studentNameText.setText(answers[position].getStudent().getName());

        holder.gradeButton.setOnClickListener(view -> {
            holder.gradeText.setEnabled(!holder.gradeText.isEnabled());
            if (holder.gradeText.isEnabled()) {
                holder.gradeButton.setText("OK");
            } else {
                holder.gradeButton.setText("Grade");
                float grade = Float.parseFloat(holder.gradeText.getText().toString());
                answers[position].setGrade(grade);
            }
        });
    }

    @Override
    public int getItemCount() {
        return answers.length;
    }

    public static class AnswersViewHolder extends RecyclerView.ViewHolder {
        protected TextView answerText;
        protected EditText gradeText;
        protected TextView studentNameText;
        protected Button gradeButton;

        @SuppressLint("SetTextI18n")
        public AnswersViewHolder(@NonNull View itemView) {
            super(itemView);

            studentNameText = itemView.findViewById(R.id.studentNameID);
            answerText = itemView.findViewById(R.id.viewStudentAnswerID);
            gradeText = itemView.findViewById(R.id.gradeID);
            gradeButton = itemView.findViewById(R.id.setGradeID);
        }
    }
}
