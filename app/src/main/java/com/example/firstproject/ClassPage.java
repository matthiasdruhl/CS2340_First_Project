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
import java.util.Calendar;

public class ClassPage extends Activity {

    String[] tempArray = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_page);
        Bundle extras = getIntent().getExtras();
        TextView title = findViewById(R.id.associated_class);
        title.setText(extras.getString("name"));
        title.setBackgroundColor(getResources().getColor(extras.getInt("color")));
        View button = findViewById(R.id.returnbutton);
        button.setBackgroundColor(getResources().getColor(extras.getInt("color")));
        ArrayList<String> assignments = extras.getStringArrayList("assignments");
        ArrayList<String> exams = extras.getStringArrayList("exams");

        LinearLayout main = findViewById(R.id.associated_color);
        for (String assignment : assignments) {
            View view = LayoutInflater.from(this).inflate(
                    R.layout.assignment_layout, null, false);
            view.setBackgroundColor(getResources().getColor(extras.getInt("color")));
            TextView name = view.findViewById(R.id.class_assignment);
            name.setText(assignment.split(";")[0]);
            TextView due_date = view.findViewById(R.id.due_date);
            due_date.setText("Due date: " + assignment.split(";")[1]);


            Button remove_assignment = view.findViewById(R.id.remove_assignment);
            remove_assignment.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    main.removeView(view);
                    Classes.getClassByName(extras.getString("name")).deleteAssignment(assignment);
                }
            });
            main.addView(view);
            Button editAssignment = view.findViewById(R.id.edit_assignment);
            editAssignment.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    setContentView(R.layout.edit_assignment);


                    final EditText textInput2 = findViewById(R.id.textInput2);
                    final EditText textInput = findViewById(R.id.textInput);
                    final EditText textInput3 = findViewById(R.id.textInput3);
                    View addbutton = findViewById(R.id.addButton);
                    textInput.setText((extras.getString("name")));
                    textInput2.setText(name.getText());
                    textInput3.setText((due_date.getText().toString()).substring(10));
                    addbutton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            if (textInput.getText().toString().isEmpty() || textInput2.getText().toString().isEmpty() || textInput3.getText().toString().isEmpty()) {
                                return;
                            }
                            tempArray[0] = textInput.getText().toString();
                            tempArray[1] = textInput2.getText().toString();
                            tempArray[2] = textInput3.getText().toString();
                            try {

                                Classes.getClassByName(tempArray[0]).addAssignment(tempArray[1] + ";" + tempArray[2]);
                                finish();
                            } catch (NullPointerException e) {
                                return;
                            }
                        }
                    });
                    main.removeView(view);
                    Classes.getClassByName(extras.getString("name")).deleteAssignment(assignment);
                }
            });

        }
        for (String exam : exams) {
            View view = LayoutInflater.from(this).inflate(
                    R.layout.exam_layout, null, false);
            TextView name = view.findViewById(R.id.class_exam);

            name.setText(exam.split(";")[0]);
            TextView location = view.findViewById(R.id.exam_location);
            location.setText("Location: " + exam.split(";")[1]);
            TextView date_time = view.findViewById(R.id.exam_dateTime);
            date_time.setText("Date/Time: " + exam.split(";")[2]);


            Button remove_assignment = view.findViewById(R.id.remove_exam);
            remove_assignment.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    main.removeView(view);
                    Classes.getClassByName(extras.getString("name")).deleteExam(exam);
                }
            });
            main.addView(view);
            Button editExam = view.findViewById(R.id.edit_exam);
            editExam.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    setContentView(R.layout.edit_exams);
                    View addbutton = findViewById(R.id.save_exam);
                    final EditText textInput2 = findViewById(R.id.textInput2);
                    final EditText textInput = findViewById(R.id.textInput);
                    final EditText textInput3 = findViewById(R.id.textInput3);
                    final EditText textInput4 = findViewById(R.id.textInput4);
                    textInput.setText((extras.getString("name")));
                    textInput2.setText(name.getText());
                    textInput3.setText((location.getText().toString()).substring(10));
                    textInput4.setText((date_time.getText().toString().substring(11)));
                    addbutton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            if (textInput.getText().toString().isEmpty() || textInput2.getText().toString().isEmpty() || textInput3.getText().toString().isEmpty() && textInput4.getText().toString().isEmpty()) {
                                return;
                            }
                            tempArray[0] = textInput.getText().toString();
                            tempArray[1] = textInput2.getText().toString();
                            tempArray[2] = textInput3.getText().toString();
                            tempArray[3] = textInput3.getText().toString();


                            try {
                                Classes.getClassByName(tempArray[0]).addAssignment(tempArray[1] + ";" + tempArray[2]);
                                finish();
                            } catch(NullPointerException e) {
                                return;
                            }
                        }
                    });

                    main.removeView(view);
                    Classes.getClassByName(extras.getString("name")).deleteExam(exam);
                }
            });
        }

        Button test = findViewById(R.id.returnbutton);
        test.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        Button adding_assignment  = findViewById(R.id.EditButton);
        adding_assignment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.edit_assignment);
                View addbutton = findViewById(R.id.addButton);
                final EditText textInput2 = findViewById(R.id.textInput2);
                final EditText textInput = findViewById(R.id.textInput);
                final EditText textInput3 = findViewById(R.id.textInput3);
                textInput.setText((extras.getString("name")));
                addbutton.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        if (textInput.getText().toString().isEmpty() || textInput2.getText().toString().isEmpty() || textInput3.getText().toString().isEmpty()) {
                            return;
                        }
                            tempArray[0] = textInput.getText().toString();
                            tempArray[1] = textInput2.getText().toString();
                            tempArray[2] = textInput3.getText().toString();
                        try {
                            Classes.getClassByName(tempArray[0]).addAssignment(tempArray[1] + ";" + tempArray[2]);
                            String dues = textInput3.getText().toString();
                            String[] arr = dues.split("/");
                            Integer day = Integer.parseInt(arr[1]);
                            Integer month = Integer.parseInt(arr[0]);
                            month = month - 1;
                            Integer year = Integer.parseInt(arr[2]);
                            MainActivity.days[MainActivity.index] = day;
                            Log.d("fffffff", day.toString());
                            MainActivity.months[MainActivity.index] = month;
                            Log.d("fffffff", month.toString());
                            MainActivity.years[MainActivity.index] = year;
                            Log.d("fffffff", year.toString());
                            MainActivity.calendarStrings.add(MainActivity.index, "Class: " +  tempArray[0] + "\nType: Assignment" + "\nName" + ":" + tempArray[1]);
                            Log.d("FDFDFDFdfd", MainActivity.calendarStrings.toString());
                            MainActivity.index++;
                            finish();
                        } catch(NullPointerException e) {
                            return;
                        } catch (Exception ex) {
                            finish();
                        }

                    }
                });
            }
        });
        Button adding_exam  = findViewById(R.id.ExamButton);
        adding_exam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.edit_exams);
                View addbutton = findViewById(R.id.save_exam);
                final EditText textInput2 = findViewById(R.id.textInput2);
                final EditText textInput = findViewById(R.id.textInput);
                final EditText textInput3 = findViewById(R.id.textInput3);
                final EditText textInput4 = findViewById(R.id.textInput4);
                textInput.setText((extras.getString("name")));
                addbutton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (textInput.getText().toString().isEmpty() || textInput2.getText().toString().isEmpty() || textInput3.getText().toString().isEmpty() || textInput4.getText().toString().isEmpty()) {
                            return;
                        }
                        try {
                            tempArray[0] = textInput.getText().toString();
                            tempArray[1] = textInput2.getText().toString();
                            tempArray[2] = textInput3.getText().toString();
                            tempArray[3] = textInput4.getText().toString();

                            Classes.getClassByName(tempArray[0]).addExams(tempArray[1] + ";" + tempArray[2] + ";" + tempArray[3]);
                            String dues = textInput4.getText().toString();
                            String[] arr = dues.split("/");
                            Integer day = Integer.parseInt(arr[1]);
                            Integer month = Integer.parseInt(arr[0]);
                            month = month - 1;
                            Integer year = Integer.parseInt(arr[2]);
                            MainActivity.days[MainActivity.index] = day;

                            MainActivity.months[MainActivity.index] = month;

                            MainActivity.years[MainActivity.index] = year;

                            MainActivity.calendarStrings.add(MainActivity.index, "Class: " +  tempArray[0] + "\nType: Exam" + "\nName" + ":" + tempArray[1] + "\nLocation: " + tempArray[2]);

                            MainActivity.index++;
                            finish();
                        } catch (NullPointerException e) {
                            return;

                        } catch (Exception ex) {
                            finish();
                        }

                    }
                });
            }
        });
    }
}
