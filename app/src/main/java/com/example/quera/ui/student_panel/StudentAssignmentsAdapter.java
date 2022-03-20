package com.example.quera.ui.student_panel;

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

import com.example.quera.MainActivity;
import com.example.quera.R;
import com.example.quera.controller.ClassController;
import com.example.quera.controller.StudentPanelController;
import com.example.quera.model.Course;
import com.example.quera.model.Student;

public class StudentAssignmentsAdapter extends RecyclerView.Adapter<StudentAssignmentsAdapter.StudentAssignmentsViewHolder> {
    StudentPanelController studentController = MainActivity.studentPanelController;
    ClassController classController = MainActivity.classController;

    protected String[] assignments, answers;
    protected Float[] grades;
    protected Context context;
    public static Student student;
    public static Course course;

    public StudentAssignmentsAdapter(StudentClassActivity ct, String[] assignmentNames, String[] studentAnswers, Float[] studentGrades, String studentName, String className) {
        context = ct;
        assignments = assignmentNames;
        answers = studentAnswers;
        grades = studentGrades;
        student = studentController.getStudentByUsername(studentName);
        course = classController.getClassByName(className);
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
        holder.assignmentNameText.setText(assignments[position]);
        holder.studentAnswerText.setText(answers[position]);
        if (answers[position].equals("")) {
            holder.answerButton.setText("Answer");
        } else {
            holder.answerButton.setText("Change");
        }
    }

    @Override
    public int getItemCount() {
        return assignments.length;
    }

    public static class StudentAssignmentsViewHolder extends RecyclerView.ViewHolder {
        StudentPanelController studentController = MainActivity.studentPanelController;
        ClassController classController = MainActivity.classController;

        protected TextView assignmentNameText, studentGradeText;
        protected EditText studentAnswerText;
        protected Button answerButton;

        @SuppressLint("SetTextI18n")
        public StudentAssignmentsViewHolder(@NonNull View itemView) {
            super(itemView);

            assignmentNameText = itemView.findViewById(R.id.assignmentNameID);
            studentGradeText = itemView.findViewById(R.id.answerGradeID);
            studentAnswerText = itemView.findViewById(R.id.studentAnswerID);
            answerButton = itemView.findViewById(R.id.answerID);

            studentAnswerText.setEnabled(false);

            answerButton.setOnClickListener(view -> {
                studentAnswerText.setEnabled(!studentAnswerText.isEnabled());
                if (studentAnswerText.isEnabled()) {
                    answerButton.setText("OK");
                } else {
                    answerButton.setText("Change");
                    String answer = studentAnswerText.getText().toString();
                    student.answerAssignment(classController.getClassAssignmentByName(course, assignmentNameText.getText().toString()), answer);
                }
            });
        }
    }
}
