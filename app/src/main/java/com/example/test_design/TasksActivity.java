package com.example.test_design;


import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TasksActivity extends AppCompatActivity {

    private View qa_home;
    private View qa_work;

    //Day of week tabs
    private View dayofweek1;
    private View dayofweek2;
    private View dayofweek3;
    private View dayofweek4;

    //User input and list view + adapter
    private EditText taskInput;
    private ListView taskListView;
    private ArrayList<String> taskList;
    private ArrayAdapter<String> taskAdapter;

    //Add task button inside popup window
    private Button add_button;

    //Add task button on main menu
    private ImageButton qa_add;

    //Add task button on main screen
    private ImageButton add_task;

    //Create a calendar method instance
    Calendar calendar = Calendar.getInstance();
    // Get the current day of the week (1 = Sunday, 2 = Monday, etc.)
    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        drawerLayout = findViewById(R.id.drawer_layout);

        //Day of week views
        dayofweek1 = findViewById(R.id.day_of_week1);
        dayofweek2 = findViewById(R.id.day_of_week2);
        dayofweek3 = findViewById(R.id.day_of_week3);
        dayofweek4 = findViewById(R.id.day_of_week4);

        //Quick access views
        qa_home = findViewById(R.id.qa_home);
        qa_work = findViewById(R.id.qa_work);
        qa_add = findViewById(R.id.qa_add);

        //List view on main screen
        taskListView = findViewById(R.id.buttom_panel);

        //Add task button
        add_task = findViewById(R.id.add_task);

        //Reads saved tasks from file
        readItems("dow1");

        //Array adapter
        taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        taskListView.setAdapter(taskAdapter);

        dayofweek1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readItems("dow1");
                taskAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, taskList);
                taskListView.setAdapter(taskAdapter);

                //Add a task and refresh list view
                add_task.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        input_popup("dow1");
                        readItems("dow1");
                        taskAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, taskList);
                        taskListView.setAdapter(taskAdapter);
                    }
                });

                //To remove a task, a user shall press and hold a selected item
                taskListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                        taskList.remove(position);
                        taskAdapter.notifyDataSetChanged();
                        writeItems("dow1");
                        return true;
                    }
                });

            }
        });

        dayofweek2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readItems("dow2");
                taskAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, taskList);
                taskListView.setAdapter(taskAdapter);

                //Add a task and refresh list view
                add_task.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        input_popup("dow2");
                        readItems("dow2");
                        taskAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, taskList);
                        taskListView.setAdapter(taskAdapter);
                    }
                });

                //To remove a task, a user shall press and hold a selected item
                taskListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                        taskList.remove(position);
                        taskAdapter.notifyDataSetChanged();
                        writeItems("dow2");
                        return true;
                    }
                });
            }
        });

        dayofweek3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readItems("dow3");
                taskAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, taskList);
                taskListView.setAdapter(taskAdapter);

                //Add a task and refresh list view
                add_task.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        input_popup("dow3");
                        readItems("dow3");
                        taskAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, taskList);
                        taskListView.setAdapter(taskAdapter);
                    }
                });

                //To remove a task, a user shall press and hold a selected item
                taskListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                        taskList.remove(position);
                        taskAdapter.notifyDataSetChanged();
                        writeItems("dow3");
                        return true;
                    }
                });
            }
        });

        dayofweek4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readItems("dow4");
                taskAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, taskList);
                taskListView.setAdapter(taskAdapter);

                //Add a task and refresh list view
                add_task.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        input_popup("dow4");
                        readItems("dow4");
                        taskAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, taskList);
                        taskListView.setAdapter(taskAdapter);
                    }
                });

                //To remove a task, a user shall press and hold a selected item
                taskListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                        taskList.remove(position);
                        taskAdapter.notifyDataSetChanged();
                        writeItems("dow4");
                        return true;
                    }
                });
            }
        });

        updateDaysOfWeek();

    }

    public void ClickMenu(View view){
        MainActivity.openDrawer(drawerLayout);
    }

    public void ClickTasks(View view){
        recreate();
    }

    public void ClickNotes(View view){
        MainActivity.redirectActivity(this, NotesActivity.class);
    }

    public void updateDaysOfWeek() {
        TextView day1TextView = findViewById(R.id.dow1);
        TextView day2TextView = findViewById(R.id.dow2);
        TextView day3TextView = findViewById(R.id.dow3);
        TextView day4TextView = findViewById(R.id.dow4);

        // Get the current date
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // Update the first day
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String day1 = sdf.format(calendar.getTime());
        day1TextView.setText(day1);

        // Update the next 3 days
        calendar.add(Calendar.DATE, 1);
        String day2 = sdf.format(calendar.getTime());
        day2TextView.setText(day2);

        calendar.add(Calendar.DATE, 1);
        String day3 = sdf.format(calendar.getTime());
        day3TextView.setText(day3);

        calendar.add(Calendar.DATE, 1);
        String day4 = sdf.format(calendar.getTime());
        day4TextView.setText(day4);
    }


    //Reads "todo.txt" file and displays the content
    private void readItems(String dow) {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, dow);
        try {
            taskList = new ArrayList<String>( FileUtils.readLines(todoFile));
        } catch (IOException e) {
            taskList = new ArrayList<String>();
        }
    }

    //Write into "todo.txt" tasks entered by user
    private void writeItems(String dow) {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir,dow);
        try {
            FileUtils.writeLines(todoFile, taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void input_popup(String dow) {
        // Create a new instance of the PopupWindow class
        PopupWindow popupWindow = new PopupWindow(this);

        // Set the layout for the popup window
        View popupView = getLayoutInflater().inflate(R.layout.input, null);
        popupWindow.setContentView(popupView);

        // Set the width and height of the popup window
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        // Set the focusable and outside touchable properties of the popup window
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        // Set the animation for the popup window
        //popupWindow.setAnimationStyle(R.style.PopupAnimation);

        // Show the popup window
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        // Get the EditText and Button views from the popup layout
        EditText taskEditText = popupView.findViewById(R.id.taskEditText);
        Button addButton = popupView.findViewById(R.id.addButton);

        // Set the click listener for the Add button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the task text from the EditText view
                String taskText = taskEditText.getText().toString();
                if(taskText.isEmpty()) {
                    // Dismiss the popup window
                    popupWindow.dismiss();
                } else {
                    // Add the task text to the ListView
                    taskList.add(taskText);
                    writeItems(dow);
                    taskAdapter.notifyDataSetChanged();
                    popupWindow.dismiss(); }
            }
        });
    }


}
