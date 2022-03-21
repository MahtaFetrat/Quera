package com.example.quera.ui.student_panel;

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
import com.example.quera.model.Assignment;
import com.example.quera.model.Course;
import com.example.quera.model.Student;

public class StudentAssignmentsAdapter extends RecyclerView.Adapter<StudentAssignmentsAdapter.StudentAssignmentsViewHolder> {

    protected String[] assignments;
    protected Context context;
    public Student student;
    public Course course;

    public StudentAssignmentsAdapter(StudentClassActivity ct, String[] assignmentNames, Student student, Course course) {
        context = ct;
        assignments = assignmentNames;
        this.student = student;
        this.course = course;
    }

    @NonNull
    @Override
    public StudentAssignmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.student_assignment_row, parent, false);

        return new StudentAssignmentsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StudentAssignmentsViewHolder holder, int position) {
        Assignment assignment = Assignment.getAssignmentById(assignments[position]);
        holder.assignmentNameText.setText(assignment.getName());
        Answer answer = assignment.getStudentAnswer(student.getUsername());
        if (answer == null) {
            holder.answerButton.setText("Send");
        } else {
            holder.studentAnswerText.setText(answer.getAnswer());
            holder.studentGradeText.setText(String.valueOf(answer.getGrade()));
            holder.answerButton.setText("Change");
            holder.answerButton.setEnabled(false);
        }

        holder.studentAnswerText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String answerText = (answer == null ? "" : answer.getAnswer());
                holder.answerButton.setEnabled(!holder.studentAnswerText.getText().toString().equals(answerText));
            }
        });

        holder.answerButton.setOnClickListener(view -> {
            String studentAnswer = holder.studentAnswerText.getText().toString();
            if (answer == null) {
                assignment.addAnswer(new Answer(student, assignment, studentAnswer));
            } else {
                answer.setAnswer(studentAnswer);
            }
            holder.studentAnswerText.clearFocus();
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return assignments.length;
    }

    public static class StudentAssignmentsViewHolder extends RecyclerView.ViewHolder {
        protected TextView assignmentNameText, studentGradeText;
        protected EditText studentAnswerText;
        protected Button answerButton;

        @SuppressLint("SetTextI18n")
        public StudentAssignmentsViewHolder(@NonNull View itemView) {
            super(itemView);

            assignmentNameText = itemView.findViewById(R.id.studentAssignmentNameID);
            studentGradeText = itemView.findViewById(R.id.answerGradeID);
            studentAnswerText = itemView.findViewById(R.id.studentAnswerEditText);
            answerButton = itemView.findViewById(R.id.answerButtonID);
        }
    }
}
