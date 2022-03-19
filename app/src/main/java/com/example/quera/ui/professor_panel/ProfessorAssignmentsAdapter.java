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
import com.example.quera.view.assignments.AnswersFragment;
import com.example.quera.view.assignments.ProfessorAssignmentsFragment;

import java.util.Objects;

public class ProfessorAssignmentsAdapter extends RecyclerView.Adapter<ProfessorAssignmentsAdapter.ProfessorAssignmentsViewHolder> {
    ClassController controller = MainActivity.classController;

    protected String[] assignments;
    @SuppressLint("StaticFieldLeak")
    protected static Context context;

    public ProfessorAssignmentsAdapter(ProfessorClassActivity ct, Object[] assignmentNames) {
        context = ct;
        assignments = (String[]) assignmentNames;
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
        holder.assignmentNameText.setText(assignments[position]);
    }

    @Override
    public int getItemCount() {
        return assignments.length;
    }

    public static class ProfessorAssignmentsViewHolder extends RecyclerView.ViewHolder {
        protected EditText assignmentNameText;
        protected Button changeAssignmentName;
        protected Button studentAnswers;
        protected String nameBeforeChange;

        @SuppressLint("SetTextI18n")
        public ProfessorAssignmentsViewHolder(@NonNull View itemView) {
            super(itemView);

            assignmentNameText = itemView.findViewById(R.id.assignmentNameID);
            changeAssignmentName = itemView.findViewById(R.id.changeNameID);
            studentAnswers = itemView.findViewById(R.id.assignmentAnswersID);

            changeAssignmentName.setOnClickListener(view -> {
                assignmentNameText.setEnabled(!assignmentNameText.isEnabled());
                if (assignmentNameText.isEnabled()) {
                    nameBeforeChange = assignmentNameText.getText().toString();
                    changeAssignmentName.setText("OK");
                } else {
                    changeAssignmentName.setText("Change name");
                    String name = assignmentNameText.getText().toString();
                    Objects.requireNonNull(Assignment.getAssignmentByName(nameBeforeChange)).setName(name);
                }
            });

            studentAnswers.setOnClickListener(view -> {
                Intent intent = new Intent(ProfessorAssignmentsAdapter.context, AnswersFragment.class);
                context.startActivity(intent);
            });
        }
    }
}
