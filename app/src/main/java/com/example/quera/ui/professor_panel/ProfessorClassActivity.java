package com.example.quera.ui.professor_panel;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quera.BaseActivity;
import com.example.quera.MainActivity;
import com.example.quera.R;
import com.example.quera.controller.ClassController;
import com.example.quera.model.Assignment;
import com.example.quera.model.Course;
import com.example.quera.model.Professor;

import java.util.ArrayList;

//public class ProfessorClassActivity extends BaseActivity {
//    ClassController controller = MainActivity.classController;
//
//    Professor professor;
//    Course clas;
//    TextView classNameTextView;
//    TextView professorNameTextView;
//    TextView assignmentsTextView;
//    EditText assignmentEditText;
//    TextView messageTextView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_student_class);
//
//        Intent intent = getIntent();
//        String username = intent.getStringExtra("username");
//        String className = intent.getStringExtra("className");
//
//        classNameTextView = findViewById(R.id.classNameStudentClassTextView);
//        professorNameTextView = findViewById(R.id.professorNameStudentClassTextView);
//        assignmentsTextView = findViewById(R.id.studentClassAssignmentsTextView);
//        assignmentEditText = findViewById(R.id.assignmentNameStudentClass);
//        messageTextView = findViewById(R.id.studentClassMessage);
//
//        this.professor = MainActivity.professorPanelController.getProfessorByUsername(username);
//        this.clas = controller.getUserClassByName(professor, className);
//        classNameTextView.setText(className);
//        professorNameTextView.setText(professor.getName());
//        assignmentsTextView.setText(controller.getClassAssignments(this.clas));
//    }
//
//    public void navToAssignment(View view) {
//        if (controller.getClassAssignmentByName(clas, assignmentEditText.getText().toString()) == null) {
//            messageTextView.setTextColor(Color.RED);
//        } else {
//                    /* TODO go to assignment page
//                    Intent intent = new Intent(...)
//                    ...
//                     */
//        }
//    }
//
//    public void createAssignment(View view) {
//        /* TODO go to create assignment page
//                Intent intent = new Intent(...)
//                 */
//    }
//}

public class ProfessorClassActivity extends AppCompatActivity implements GetNameDialog.GetNameDialogListener {
    ClassController controller = MainActivity.classController;

    protected Course course;
    protected Professor professor;
    protected ArrayList<Assignment> classAssignments;
    protected ArrayList<String> assignmentsName;
    private RecyclerView recyclerView;
    private TextView classNameTextView;
    private TextView professorNameTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_professor_class);

        recyclerView = findViewById(R.id.ProfessorAssignmentsRecyclerView);
        classNameTextView = findViewById(R.id.professorClassActivityClassName);
        professorNameTextView = findViewById(R.id.professorClassActivityProfessorName);


        Intent intent = getIntent();
        course = controller.getClassByName(intent.getStringExtra("className"));
        classAssignments = course.getAssignments();

        for (Assignment assignment : classAssignments) {
            assignmentsName.add(assignment.getName());
        }

        if (assignmentsName != null) {
            ProfessorAssignmentsAdapter professorAssignmentsAdapter = new ProfessorAssignmentsAdapter(this, assignmentsName.toArray());
            recyclerView.setAdapter(professorAssignmentsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        professor = MainActivity.professorPanelController.getProfessorByUsername(intent.getStringExtra("username"));
        professorNameTextView.setText(professorNameTextView.getText().toString() + " " + professor.getName());
        classNameTextView.setText(classNameTextView.getText().toString() + " " + course.getName());
    }

    public void addAssignment(View view) {
        GetNameDialog getNameDialog = new GetNameDialog();
        getNameDialog.show(getSupportFragmentManager(), "getNameDialog");
    }

    @Override
    public void applyName(String name) {
        course.addAssignment(name);
        assignmentsName.add(name);
    }
}
