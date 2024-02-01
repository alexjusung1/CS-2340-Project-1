package com.example.cs2340project1.ui.to_do;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cs2340project1.R;

import java.util.ArrayList;

public class ToDoFragment extends Fragment {

    private EditText editTextTask;
    private Button buttonAdd;
    private ListView listViewTasks;
    private ArrayList<String> taskList;
    private ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do, container, false);

        editTextTask = view.findViewById(R.id.editTextTask);
        buttonAdd = view.findViewById(R.id.buttonAdd);
        listViewTasks = view.findViewById(R.id.listViewTasks);

        taskList = new ArrayList<>();
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, taskList);
        listViewTasks.setAdapter(adapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask();
            }
        });

        return view;
    }

    private void addTask() {
        String task = editTextTask.getText().toString().trim();
        if (!task.isEmpty()) {
            taskList.add(task);
            adapter.notifyDataSetChanged();
            editTextTask.setText("");
        }
    }
}