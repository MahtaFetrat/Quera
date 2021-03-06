package com.example.quera;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.quera.controller.DataController;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout_action) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            alert.setTitle("Logout");
            alert.setMessage("Are you sure you want to logout?");

            alert.setPositiveButton("Yes", (dialog, whichButton) -> {
                DataController.logout();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                writeData();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                this.finish();
            });

            alert.setNegativeButton("Cancel", (dialog, whichButton) -> {
            });

            alert.show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void writeData() {
        SharedPreferences sharedPref = this.getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(getString(R.string.saved_students_key), DataController.getStudentsDataString());
        editor.putString(getString(R.string.saved_professors_key), DataController.getProfessorsDataString());
        editor.putString(getString(R.string.saved_classes_key), DataController.getClassesDataString());
        editor.putString(getString(R.string.saved_assignments_key), DataController.getAssignmentsDataString());
        editor.putString(getString(R.string.saved_answers_key), DataController.getAnswersDataString());

        editor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();
        writeData();
    }
}