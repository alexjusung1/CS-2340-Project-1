package com.example.cs2340project1.ui.to_do;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340project1.R;
import com.example.cs2340project1.databinding.FragmentToDoBinding;

public class ToDoFragment extends Fragment {

    private ToDoViewModel toDoViewModel;
    private ToDoAdapter toDoAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toDoViewModel = new ViewModelProvider(this).get(ToDoViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do, container, false);

        // Use the correct IDs
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        EditText toDoEditText = view.findViewById(R.id.toDoEditText);
        Button addButton = view.findViewById(R.id.addButton);

        toDoAdapter = new ToDoAdapter(requireContext(), toDoViewModel.getTasks(), toDoViewModel);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(toDoAdapter);

        addButton.setOnClickListener(v -> {
            String taskName = toDoEditText.getText().toString();
            if (!TextUtils.isEmpty(taskName)) {
                Task task = new Task(taskName);
                toDoViewModel.addTask(task);
                toDoAdapter.notifyDataSetChanged();
                toDoEditText.setText("");
            }
        });

        return view;
    }
}