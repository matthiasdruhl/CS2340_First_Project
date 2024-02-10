package com.example.firstproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.firstproject.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity implements TaskAdapter.OnEditClickListener{
    private int currentYear = 0;
    private int currentMonth = 0;
    private int currentDay = 0;
    static public int index = 0;
    private int i = 0;
    private List<Task> taskList;
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    final static List<String> calendarStrings = new ArrayList<>();
    final List<Task> todoArray = new ArrayList<>();
    final int numberOfDays = 2000;
    static int[] days = new int[100];
    static final int[] months = new int[100];
    static final int[] years = new int[100];


    String[] tempArray = new String[4];
    private int[] colors = {R.color.red, R.color.lemon, R.color.ashGrey, R.color.purple, R.color.white2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createClasses();
//        Button test = findViewById(R.id.homePage);
//        test.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, MainActivity.class));
//            }
//        });

    }

    //Add Class
    public void mainLayoutButton(View view, Class c) {
        setContentView(R.layout.edit);

    }
    //(plus button that leads to edit.page)
    public void homeLayoutButton(View view) {
        setContentView(R.layout.edit);
        Button add_class = findViewById(R.id.saveTextButton);
        Button cancel = findViewById(R.id.returnButton);
        final EditText textInput2 = findViewById(R.id.textInput2);
        final EditText textInput = findViewById(R.id.textInput);
        final EditText textInput3 = findViewById(R.id.textInput3);
        final EditText textInput4 = findViewById(R.id.textInput4);
        add_class.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (textInput.getText().toString().isEmpty()) {
                    return;
                }
                tempArray[0] = textInput.getText().toString();
                tempArray[1] = textInput2.getText().toString();
                tempArray[2] = textInput3.getText().toString();
                tempArray[3] = textInput4.getText().toString();
                textInput.setText("");
                textInput2.setText("");
                textInput3.setText("");
                textInput4.setText("");
                Class newClass = new Class(tempArray[0], tempArray[1], tempArray[2], tempArray[3]);
                Classes.addClass(newClass);
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    public void secondLayoutButton() {
        setContentView(R.layout.calendar);
        navBar();
        final CalendarView calendarView = findViewById(R.id.calendarView);
        final EditText textInput = findViewById(R.id.textInput);
        final EditText textInput2 = findViewById(R.id.textInput2);
        final EditText textInput3 = findViewById(R.id.textInput3);
        final View dayContent = findViewById(R.id.dayContent);
        final TextView textView2 = findViewById(R.id.textView2);
        final EditText textInput4 = findViewById(R.id.textInput4);
        final EditText textInput5 = findViewById(R.id.textInput5);
        final Button saveTextButton = findViewById(R.id.saveTextButton);
        textView2.setText("Please Select a Day");
        saveTextButton.setVisibility(View.GONE);
        textInput.setVisibility(View.GONE);
        textInput2.setVisibility(View.GONE);
        textInput3.setVisibility(View.GONE);
        textInput4.setVisibility(View.GONE);
        textInput5.setVisibility(View.GONE);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i4, int i1, int i2) {
                saveTextButton.setVisibility(View.VISIBLE);
                textInput.setVisibility(View.VISIBLE);
                textInput2.setVisibility(View.VISIBLE);
                textInput3.setVisibility(View.VISIBLE);
                textInput4.setVisibility(View.VISIBLE);
                textInput5.setVisibility(View.VISIBLE);
                textView2.setText("Add to Calendar");
                currentYear = i4;
                currentMonth = i1;
                currentDay = i2;
                if (dayContent.getVisibility() == CalendarView.GONE) {
                    dayContent.setVisibility(View.VISIBLE);
                }
                for (int h = 0; h < index; h++) {
                    if (years[h] == i4) {
                        for (int j = 0; j < index; j++) {
                            if (days[j] == currentDay) {
                                for (int k = 0; k < index; k++) {
                                    if (months[k] == currentMonth && days[k] == currentDay && years[k] == currentYear && index == calendarStrings.size()) {
                                        textView2.setText(calendarStrings.get(k));
                                    }
                                }
                            }
                        }
                    }
                }
                textInput.setText("");
                textInput2.setText("");
                textInput3.setText("");
                textInput4.setText("");
                textInput5.setText("");

            }
        });


        saveTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                days[index] = currentDay;
                months[index] = currentMonth;
                years[index] = currentYear;
                String tempString = textInput.getText().toString();
                String tempString2 = textInput2.getText().toString();
                String tempString3 = textInput3.getText().toString();
                String tempString4 = textInput4.getText().toString();
                String tempString5 = textInput5.getText().toString();

                // problem is with having duplicates the for loops search for the first duplicate
                calendarStrings.add(index, "Class: " +  tempString + "\nType: " + tempString4 + "\nName" + ":" + tempString5 + "\nLocation: " + tempString2 + "\nTime: " + tempString3 + "\n");
                textView2.setText(calendarStrings.get(index));
                textInput.setText("");
                textInput2.setText("");
                textInput3.setText("");
                textInput4.setText("");
                textInput5.setText("");
                String dueDate = Integer.toString(currentMonth + 1) + "/" + Integer.toString(currentDay) + "/" + Integer.toString(currentYear);
                index++;
                if (tempString4.equals(("assignment").toLowerCase(Locale.ROOT))) {
                    try {
                        Classes.getClassByName(tempString).addAssignment(tempString5 + ";" + dueDate);
                    }
                    catch (NullPointerException e) {
                        return;
                    }
                }
                if (tempString4.equals(("exam").toLowerCase(Locale.ROOT))) {
                    try {
                        Classes.getClassByName(tempString).addExams(tempString5 + ";" + tempString2 + ";" + dueDate);
                    }
                    catch (NullPointerException e) {
                        return;
                    }
                }
            }
        });
    }



        private void navBar() {
        Button home_button = findViewById(R.id.homePage);
        Button calendar_button = findViewById(R.id.calendar_button);
        Button todo_button = findViewById(R.id.todo_button);
        home_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.activity_main);
                createClasses();
            }
        });
        calendar_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                secondLayoutButton();
            }
        });
        todo_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toDoList();
            }
        });
    }

    private void createClasses() {
        LinearLayout main = findViewById(R.id.main);
        for (int i = 0; i < Classes.getClasses().size(); i++) {
            Class _class = Classes.get(i);
            int color = colors[i % colors.length];
            View view = LayoutInflater.from(this).inflate(
                    R.layout.classlayout, null, false);
            Button btn = (Button) ((ViewGroup) view).getChildAt(0);
            View details = ((ViewGroup) view).getChildAt(1);
            details.setBackgroundColor(getResources().getColor(color));

            TextView instructor = (TextView) ((ViewGroup) details).getChildAt(0);
            TextView location = (TextView) ((ViewGroup) details).getChildAt(1);
            TextView time = (TextView) ((ViewGroup) details).getChildAt(2);

            View linear = ((ViewGroup) details).getChildAt(3);
            View edit_button = ((ViewGroup) linear).getChildAt(0);
            edit_button.setBackgroundColor(getResources().getColor(color));
            View delete_button = ((ViewGroup) linear).getChildAt(1);
            delete_button.setBackgroundColor(getResources().getColor(color));
            instructor.setText("Instructor: " + _class.getInstructor());
            location.setText("Location: " + _class.getLocation());
            time.setText("Date/Time: " + _class.getTime());

            btn.setText(_class.getName());
            btn.setBackgroundColor(getResources().getColor(color));
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ClassPage.class);
                    intent.putExtra("name", _class.getName());
                    intent.putExtra("color", color);
                    intent.putExtra("assignments", _class.getAssignments());
                    intent.putExtra("exams", _class.getExams());
                    startActivity(intent);
                }
            });
            Button remove_assignment = view.findViewById(R.id.remove_class);
            remove_assignment.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    main.removeView(view);
                    Classes.deleteClass(_class);
                }
            });
            Class copy = Classes.get(i);
            Button edit = view.findViewById(R.id.edit_class);
            edit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    homeLayoutButton(v);
                    main.removeView(view);
                    Classes.deleteClass(_class);
                }
            });

            main.addView(view);
        }
        navBar();
    }

    public void toDoList() {
        setContentView(R.layout.todomain);
        navBar();
        taskList = todoArray;
        taskAdapter = new TaskAdapter(taskList, this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);

        FloatingActionButton addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewTask();
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // Do nothing on item click
            }

            @Override
            public void onLongItemClick(View view, int position) {
                showOptionsDialog(position);
            }
        }));
    }
    private void addNewTask() {
        taskList.add(new Task("New Task"));
        taskAdapter.notifyDataSetChanged();
    }

    private void showOptionsDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Options");
        builder.setItems(new CharSequence[]{"Edit", "Delete"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        editTask(position);
                        break;
                    case 1:
                        deleteTask(position);
                        break;
                }
            }
        });
        builder.create().show();
    }

    private void editTask(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Task");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setText(taskList.get(position).getName());
        builder.setView(input);
        input.setText("");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String editedTaskName = input.getText().toString().trim();
                if (!editedTaskName.isEmpty()) {
                    taskList.get(position).setName(editedTaskName);
                    taskAdapter.notifyItemChanged(position);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void deleteTask(int position) {
        taskList.remove(position);
        taskAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onEditClick(int position) {
        editTask(position);
    }

}