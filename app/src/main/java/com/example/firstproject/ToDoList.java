package com.example.firstproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class ToDoList extends Activity {

    String[] tempArray = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_list);
        Bundle extras = getIntent().getExtras();
        ArrayList<String> assignments = extras.getStringArrayList("assignments");
        ArrayList<String> exams = extras.getStringArrayList("exams");

        LinearLayout main = findViewById(R.id.toDoList);
        for (String assignment : assignments) {
            View view = LayoutInflater.from(this).inflate(
                    R.layout.assignment_layout, null, false);
            TextView name = view.findViewById(R.id.class_assignment);
            name.setText(assignment.split(";")[0]);
            TextView due_date = view.findViewById(R.id.due_date);
            due_date.setText("Due date: " + assignment.split(";")[1]);
            main.addView(view);
            for (String exam : exams) {
                View v = LayoutInflater.from(this).inflate(
                        R.layout.exam_layout, null, false);
                TextView n = v.findViewById(R.id.class_exam);
                n.setText(exam.split(";")[0]);
                TextView location = v.findViewById(R.id.exam_location);
                location.setText("Location: " + exam.split(";")[1]);
                TextView date_time = v.findViewById(R.id.exam_dateTime);
                date_time.setText("Date/Time: " + exam.split(";")[2]);
                main.addView(view);
            }


        }
    }
}

