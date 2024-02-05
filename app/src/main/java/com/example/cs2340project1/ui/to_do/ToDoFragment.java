package com.example.cs2340project1.ui.to_do;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340project1.R;
import com.example.cs2340project1.ui.to_do.Adapter.ToDoAdapter;
import com.example.cs2340project1.ui.to_do.Model.ToDoModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToDoFragment extends AppCompatActivity {
    private RecyclerView toDoRecycylerView;
    private ToDoAdapter tasksAdapter;
    private List<ToDoModel> toDoList;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_to_do);
        getSupportActionBar().hide();

        toDoList = new ArrayList<>();

        toDoRecycylerView = findViewById(R.id.ToDoRecyclerView);
        toDoRecycylerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new ToDoAdapter(this);
        toDoRecycylerView.setAdapter(tasksAdapter);

        ToDoModel task = new ToDoModel();
        task.setTask("Math Test Due Tomorrow");
        task.setStatus(0);
        task.setId(1);

        toDoList.add(task);
        toDoList.add(task);
        toDoList.add(task);
        toDoList.add(task);
        toDoList.add(task);

        tasksAdapter.setTasks(toDoList);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });

    }

}