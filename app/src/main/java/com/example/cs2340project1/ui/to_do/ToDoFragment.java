package com.example.cs2340project1.ui.to_do;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import

import com.example.cs2340project1.R;
import com.example.cs2340project1.databinding.FragmentToDoBinding;

public class ToDoFragment extends Fragment {

    private EditText editTextTask;
    private Button buttonAdd;
    private ToDoViewModel viewModel;
    private FragmentToDoBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentToDoBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(requireActivity()).get(ToDoViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        editTextTask = binding.getRoot().findViewById(R.id.editTextTask);
        buttonAdd = binding.getRoot().findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(v -> addTask());

        return view;
    }

    private void addTask() {
        String task = editTextTask.getText().toString().trim();
        if (!task.isEmpty()) {
            viewModel.addItem(task);
            editTextTask.setText("");
        }
    }
}