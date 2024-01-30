package com.example.cs2340project1.ui.to_do;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340project1.ToDoListAdapter;
import com.example.cs2340project1.databinding.FragmentToDoBinding;

public class ToDoFragment extends Fragment {

    private static String[] toDoListNames = {};
    private FragmentToDoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ToDoViewModel toDoViewModel =
                new ViewModelProvider(this).get(ToDoViewModel.class);

        binding = FragmentToDoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView toDoList = binding.toDoListItems;

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        toDoList.setLayoutManager(manager);
        toDoList.setAdapter(new ToDoListAdapter(toDoListNames));

//        final TextView textView = binding.textNotifications;
//        toDoViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}