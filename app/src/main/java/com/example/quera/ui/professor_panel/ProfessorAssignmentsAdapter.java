package com.example.quera.ui.professor_panel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quera.MainActivity;
import com.example.quera.R;
import com.example.quera.controller.ClassController;
import com.example.quera.model.Assignment;
import com.example.quera.model.Course;
import com.example.quera.view.assignments.AnswersActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProfessorAssignmentsAdapter extends RecyclerView.Adapter<ProfessorAssignmentsAdapter.ProfessorAssignmentsViewHolder> {
    ClassController controller = MainActivity.classController;

    protected String[] assignments;
    @SuppressLint("StaticFieldLeak")
    public static Context context;
    public static Course course;

    public ProfessorAssignmentsAdapter(ProfessorClassActivity ct, String[] assignmentNames, String courseName) {
        context = ct;
        assignments = assignmentNames;
        course = controller.getClassByName(courseName);
    }

    @NonNull
    @Override
    public ProfessorAssignmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.professor_assignment_row, parent, false);

        return new ProfessorAssignmentsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProfessorAssignmentsViewHolder holder, int position) {
        holder.assignmentNameText.setText(Assignment.getAssignmentById(assignments[position]).getName());

        holder.changeAssignmentName.setOnClickListener(view -> {
            holder.assignmentNameText.setEnabled(!holder.assignmentNameText.isEnabled());
            if (holder.assignmentNameText.isEnabled()) {
                holder.changeAssignmentName.setImageResource(R.drawable.ic_baseline_done_24);
            } else {
                holder.changeAssignmentName.setImageResource(R.drawable.ic_baseline_edit_24);
                String name = holder.assignmentNameText.getText().toString();
                Assignment.getAssignmentById(assignments[position]).setName(name);
            }
        });

        holder.studentAnswers.setOnClickListener(view -> {
            Intent intent = new Intent(ProfessorAssignmentsAdapter.context, AnswersActivity.class);
            intent.putExtra("enteredAssignmentId", assignments[position]);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return assignments.length;
    }

    public static class ProfessorAssignmentsViewHolder extends RecyclerView.ViewHolder {
        protected EditText assignmentNameText;
        protected FloatingActionButton changeAssignmentName;
        protected Button studentAnswers;

        @SuppressLint("SetTextI18n")
        public ProfessorAssignmentsViewHolder(@NonNull View itemView) {
            super(itemView);

            assignmentNameText = itemView.findViewById(R.id.assignmentNameID);
            changeAssignmentName = itemView.findViewById(R.id.changeNameID);
            studentAnswers = itemView.findViewById(R.id.assignmentAnswersID);
        }
    }
}
