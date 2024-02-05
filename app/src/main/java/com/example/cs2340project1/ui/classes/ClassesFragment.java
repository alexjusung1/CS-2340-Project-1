package com.example.cs2340project1.ui.classes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340project1.databinding.FragmentClassesBinding;

import java.util.ArrayList;
import java.util.List;

public class ClassesFragment extends Fragment {

    private FragmentClassesBinding binding;
    private RecyclerView classRecyclerView;
    private ClassesAdapter classesAdapter;
    private List<ClassObj> classList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ClassesViewModel classesViewModel =
                new ViewModelProvider(this).get(ClassesViewModel.class);

        binding = FragmentClassesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        classRecyclerView = binding.classList;
        classRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        classesAdapter = new ClassesAdapter(this);
        classRecyclerView.setAdapter(classesAdapter);


        classList = new ArrayList<ClassObj>();
        classList.add(new ClassObj("CS 2340", "Pedroguillermo Feijoogarcia",
                "T Th", "2:00 - 3:15"));

        classesAdapter.setClasses(classList);
//        final TextView textView = binding.textHome;
//        classesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classList.add(new ClassObj(binding.classNameField.getText().toString(),
                        binding.classInstructorField.getText().toString(),
                        binding.classDaysField.getText().toString(),
                        binding.classTimeField.getText().toString()));
                classesAdapter.setClasses(classList);
                binding.classNameField.setText("");
                binding.classInstructorField.setText("");
                binding.classDaysField.setText("");
                binding.classTimeField.setText("");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}