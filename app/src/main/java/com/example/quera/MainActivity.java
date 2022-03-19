package com.example.quera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.quera.controller.ClassController;
import com.example.quera.controller.DataController;
import com.example.quera.controller.ProfessorPanelController;
import com.example.quera.controller.StudentPanelController;
import com.example.quera.model.Professor;
import com.example.quera.model.User;
import com.example.quera.ui.professor_panel.ProfessorPanelActivity;
import com.example.quera.ui.student_panel.StudentPanelActivity;
import com.example.quera.view.login_signup.LoginActivity;

public class MainActivity extends BaseActivity {
    public static DataController dataController = new DataController();
    public static StudentPanelController studentPanelController = new StudentPanelController();
    public static ProfessorPanelController professorPanelController = new ProfessorPanelController();
    public static ClassController classController = new ClassController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readData();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (dataController.getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            startActivity(new Intent(this, (dataController.getCurrentUser() instanceof Professor ? ProfessorPanelActivity.class : StudentPanelActivity.class)));
        }
    }

    private void writeData() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(getString(R.string.saved_students_key), dataController.getStudentsDataString());
        editor.putString(getString(R.string.saved_professors_key), dataController.getProfessorsDataString());
        editor.putString(getString(R.string.saved_classes_key), dataController.getClassesDataString());

        editor.apply();
    }

    private void readData() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        String studentsDataString = sharedPref.getString(getString(R.string.saved_students_key), dataController.getStudentsDataString());
        String professorsDataString = sharedPref.getString(getString(R.string.saved_professors_key), dataController.getProfessorsDataString());
        String classesDataString = sharedPref.getString(String.valueOf(R.string.saved_classes_key), dataController.getClassesDataString());

        dataController.readStudentsDataString(studentsDataString);
        dataController.readProfessorsDataString(professorsDataString);
        dataController.readClassesDataString(classesDataString);
    }

    @Override
    protected void onStop() {
        // call the superclass method first
        super.onStop();
        writeData();
    }
}