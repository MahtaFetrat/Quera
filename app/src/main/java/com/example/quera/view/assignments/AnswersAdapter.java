package com.example.quera.view.assignments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
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
        String grade = String.valueOf(answers[position].getGrade());
        holder.gradeText.setText(grade);
        holder.studentNameText.setText(answers[position].getStudent().getFullName());

        holder.gradeText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (!grade.equals(holder.gradeText.getText().toString())) {
                    holder.gradeButton.setEnabled(true);
                }
            }
        });

        holder.gradeButton.setOnClickListener(view -> {
            holder.gradeButton.setText("Grade");
            float newGrade = Float.parseFloat(holder.gradeText.getText().toString());
            answers[position].setGrade(newGrade);
            holder.gradeButton.setEnabled(false);
            holder.gradeText.clearFocus();
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
