package com.example.quera;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.quera.controller.ClassController;
import com.example.quera.controller.DataController;
import com.example.quera.controller.ProfessorPanelController;
import com.example.quera.controller.StudentPanelController;
import com.example.quera.model.Professor;
import com.example.quera.ui.professor_panel.ProfessorPanelActivity;
import com.example.quera.ui.student_panel.StudentPanelActivity;
import com.example.quera.view.login_signup.LoginActivity;

public class MainActivity extends BaseActivity {
    public static StudentPanelController studentPanelController = new StudentPanelController();
    public static ProfessorPanelController professorPanelController = new ProfessorPanelController();
    public static ClassController classController = new ClassController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readData();
    }

    private void readData() {
        SharedPreferences sharedPref = this.getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);

        String studentsDataString = sharedPref.getString(getString(R.string.saved_students_key), DataController.getStudentsDataString());
        String professorsDataString = sharedPref.getString(getString(R.string.saved_professors_key), DataController.getProfessorsDataString());
        String classesDataString = sharedPref.getString(String.valueOf(R.string.saved_classes_key), DataController.getClassesDataString());

        DataController.readStudentsDataString(studentsDataString);
        DataController.readProfessorsDataString(professorsDataString);
        DataController.readClassesDataString(classesDataString);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (DataController.getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            startActivity(new Intent(this, (DataController.getCurrentUser() instanceof Professor ? ProfessorPanelActivity.class : StudentPanelActivity.class)));
            finish();
        }
    }
}